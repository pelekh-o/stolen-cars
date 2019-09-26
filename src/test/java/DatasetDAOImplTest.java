import entity.Dataset;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import persistence.Factory;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.*;

public class DatasetDAOImplTest {
    Dataset dataset;

    @Before public void initialize() {
        dataset = new Dataset("https://data.gov.ua/dataset/9b0e87e0-eaa3-4f14-9547-03d61b70abb6/resource" +
                "/06e65b06-3120-4713-8003-7905a83f95f5/download/mvswantedtransport_1.json", -1);
    }

    @Ignore
    @Test
    public void addDataset() {
        try {
            Factory.getInstance().getDatasetDAO().addDataset(dataset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDatasetById() {
        Dataset testSet = null;
        try {
            testSet = Factory.getInstance().getDatasetDAO().getDatasetById(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertTrue(testSet.getCarscount() == 0);
    }

    @Test
    public void getAllDataset() {
        try {
            assertNotNull(Factory.getInstance().getDatasetDAO().getAllDataset());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void updateDataset() {
    }

    @Test
    public void getLastUpdate() {
        Dataset testSet = null;
        try {
            testSet = Factory.getInstance().getDatasetDAO().getLastUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertTrue(testSet.getUpdated().getDay() == new Date().getDay());
    }
}