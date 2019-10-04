package dao;

import entity.CarInfo;

import java.sql.SQLException;
import java.util.List;

public interface CarInfoDAO {
    void addCar(CarInfo car) throws SQLException;
    CarInfo getCarById(Integer id) throws SQLException;
    List getAllCars() throws SQLException;
    List getCarByVehicleNumber(String vehicleNumber) throws SQLException;
}
