package parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import entity.Car;
import entity.Dataset;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import persistence.Factory;
import services.DateUtil;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;

public class Parser {
    public final static String DATA_GOV_UA_URL = "https://data.gov.ua/dataset/2a746426-b289-4eb2-be8f-aac03e68948c";
    private Logger log = Logger.getLogger(getClass().getName());
    private String datasetURL;

    public Parser() {
        datasetURL = getDatasetURL();
        log.info("Dataset URL: " + datasetURL);
    }

    /**
     * Method compares the date of last update of local DB and
     * the date specified in tag "automatic-local-datetime"
     * at http://data.gov.ua/view-dataset/dataset-file/.....
     * @return true if an update is needed
     */
    public boolean isUpdateNeeded () throws Exception {
        Dataset dataset = Factory.getInstance().getDatasetDAO().getLastUpdate();
        Date dbUpdDate = dataset.getUpdated();

        Document infoPage = Jsoup.connect(DATA_GOV_UA_URL).get();
        Date pageUpdDate =
                DateUtil.FORMATTER.parse(infoPage.getElementsByClass("automatic-local-datetime").
                first().attr("data-datetime"));

        log.info("Local DB updated " + dbUpdDate + ", remote DB updated " + pageUpdDate);
        log.info("Is update needed: " + dbUpdDate.before(pageUpdDate));
        if(dbUpdDate.before(pageUpdDate)){
            return true;
        } else {
            Factory.getInstance().getDatasetDAO().updateDataset(dataset, new Date());
            return false;
        }
    }

    /**
     *
     * @throws Exception
     */
    public void retrieveUpdates() throws IOException, SQLException {
        // W/ help: http://www.acuriousanimal.com/2015/10/23/reading-json-file-in-stream-mode-with-gson.html

        int carsCount = 0;

        URL url = new URL(datasetURL);
        URLConnection conn = url.openConnection();
        JsonReader reader = new JsonReader(
                new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)));

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        // Read file in stream mode
        reader.beginArray();
        while (reader.hasNext()) {
            Car car = gson.fromJson(reader, Car.class);
            carsCount++;
            if (!Factory.getInstance().getCarDAO().isCarExists(car)) {
                Factory.getInstance().getCarDAO().addCar(car);
                log.info("New car found: " + car);
            }
        }
        reader.close();
        Factory.getInstance().getDatasetDAO().addDataset(new Dataset(datasetURL, carsCount));
    }

    private String getDatasetURL() {
        Document document = null;
        try {
            document = Jsoup.connect(DATA_GOV_UA_URL).get();
        } catch (IOException e) {
            e.printStackTrace();
            log.warn(e.getMessage());
        }
        // Get URL of json file
        return document.getElementsByClass("resource-url-analytics").attr("abs:href");
    }
}
