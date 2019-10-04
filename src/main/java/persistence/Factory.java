package persistence;

import dao.CarDAO;
import dao.CarInfoDAO;
import dao.DatasetDAO;
import dao.UserDAO;
import dao.implementation.CarDAOImpl;
import dao.implementation.CarInfoDAOImpl;
import dao.implementation.DatasetDAOImpl;
import dao.implementation.UserDAOImpl;

public class Factory {
    private static CarDAO carDAO = null;
    private static DatasetDAO datasetDAO = null;
    private static UserDAO userDAO = null;
    private static CarInfoDAO carInfoDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public CarDAO getCarDAO() {
        if (carDAO == null) {
            carDAO = new CarDAOImpl();
        }
        return carDAO;
    }

    public DatasetDAO getDatasetDAO() {
        if (datasetDAO == null) {
            datasetDAO = new DatasetDAOImpl();
        }
        return datasetDAO;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

    public CarInfoDAO getCarInfoDAO() {
        if (carInfoDAO == null) {
            carInfoDAO = new CarInfoDAOImpl();
        }
        return carInfoDAO;
    }

}