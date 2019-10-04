package carInfo;

import entity.CarInfo;
import org.apache.log4j.Logger;
import persistence.Factory;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarInfoUtil {

    private static Logger log = Logger.getLogger(CarInfoUtil.class);

    public static void main(String[] args) throws IOException {

        CarInfoUtil util = new CarInfoUtil();

        String csvFilesFolder = "";
        if (args.length != 0 )
            csvFilesFolder = args[0];
        else {
            System.out.print("Enter path to the folder with csv files: ");
            Scanner scan = new Scanner(System.in);
            csvFilesFolder = scan.next();
        }

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        File[] files = util.getFiles(csvFilesFolder);

        try {
            for (File file : files)
                executor.execute(new MyRunnable(file.getAbsolutePath()));
        } catch(Exception err) {
            err.printStackTrace();
        }
        executor.shutdown();
        log.info(files.length + " files found in " + csvFilesFolder);
        //for (File file : files) {
            //util.saveAllCars(file.getAbsolutePath());

        //}

    }

    private File[] getFiles(String directoryPath) {
        File dir = new File(directoryPath);
        return dir.listFiles((d, name) -> name.toLowerCase().endsWith(".csv"));
    }

    /*private void saveAllCars(String path) throws IOException {
        String row = "";
        BufferedReader csvReader = new BufferedReader(new FileReader(path));
        csvReader.readLine();       // read headers row

        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(";");
            try {
                CarInfo carInfo = new CarInfo();
                carInfo.setRegAddrKoatuu(data[1]);
                carInfo.setOperationCode(data[2]);
                carInfo.setOperationName(removeQuotes(data[3]).replaceAll("^\\d*.{3}", ""));
                carInfo.setRegdate(Date.valueOf(data[4]));
                carInfo.setDepartmentCode(data[5]);
                carInfo.setBrand(removeQuotes(data[7]));
                carInfo.setModel(removeQuotes(data[8]));
                carInfo.setMakeYear(tryParse(data[9]));
                carInfo.setColor(data[10]);
                carInfo.setKind(data[11]);
                carInfo.setBody(data[12]);
                carInfo.setFuel(removeQuotes(data[14]));
                carInfo.setCapacity(tryParse(data[15]));
                carInfo.setOwnWeight(tryParse(data[16]));
                carInfo.setTotalWeight(tryParse(data[17]));
                carInfo.setVehicleNumber(data[18]);

                insertCarInfoToDB(carInfo);
            } catch (Exception e) {
                log.warn(e.toString());
            }
        }
        csvReader.close();
    }*/

    static void insertCarInfoToDB(CarInfo carInfo) {
        try {
            Factory.getInstance().getCarInfoDAO().addCar(carInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static String removeQuotes(String text) {
        return text.replaceAll("\"", "");
    }
}

class MyRunnable implements Runnable {
    private static Logger log = Logger.getLogger(MyRunnable.class);

    private String path;

    public MyRunnable(String path) {
        this.path = path;
    }

    public void run() {
        Thread.currentThread().setName(path);
        System.out.println(Thread.currentThread().getId());

        String row = "";
        BufferedReader csvReader = null;

        try {
            csvReader = new BufferedReader(new FileReader(path));
            csvReader.readLine();
        } catch (IOException e) {
            log.error(e.toString());
        }

        while (true) {
            try {
                if ((row = csvReader.readLine()) == null) break;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            String[] data = row.split(";");
            try {
            CarInfo carInfo = new CarInfo();
            carInfo.setRegAddrKoatuu(data[1]);
            carInfo.setOperationCode(data[2]);
            carInfo.setOperationName(CarInfoUtil.removeQuotes(data[3]).replaceAll("^\\d*.{3}", ""));
            carInfo.setRegdate(Date.valueOf(data[4]));
            carInfo.setDepartmentCode(data[5]);
            carInfo.setBrand(CarInfoUtil.removeQuotes(data[7]));
            carInfo.setModel(CarInfoUtil.removeQuotes(data[8]));
            carInfo.setMakeYear(CarInfoUtil.tryParse(data[9]));
            carInfo.setColor(data[10]);
            carInfo.setKind(data[11]);
            carInfo.setBody(data[12]);
            carInfo.setFuel(CarInfoUtil.removeQuotes(data[14]));
            carInfo.setCapacity(CarInfoUtil.tryParse(data[15]));
            carInfo.setOwnWeight(CarInfoUtil.tryParse(data[16]));
            carInfo.setTotalWeight(CarInfoUtil.tryParse(data[17]));
            carInfo.setVehicleNumber(data[18]);

            CarInfoUtil.insertCarInfoToDB(carInfo);
            } catch (Exception e) {
                log.warn(e.toString() + " | " + path);
            }
        }
        try {
            csvReader.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
