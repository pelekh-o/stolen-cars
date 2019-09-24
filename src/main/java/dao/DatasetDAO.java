package dao;

import entity.Dataset;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface DatasetDAO {
    void addDataset(Dataset dataset) throws SQLException;
    void updateDataset(Dataset dataset, Date updated) throws SQLException;
    Dataset getDatasetById(Integer Id) throws SQLException;
    Collection getAllDataset() throws SQLException;
    Dataset getLastUpdate() throws SQLException;
}
