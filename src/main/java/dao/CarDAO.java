package dao;

import entity.Car;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface CarDAO {
    boolean addCar(Car car) throws SQLException;
    Car getCarById(Integer Id) throws SQLException;
    List getAllCars() throws SQLException;
    List getCarByVehicleNumber(String vehicleNumber) throws SQLException;
    List getCarByTheftDate(String theftDate) throws SQLException;
    List getCarByInsertDate(Date insertDate) throws SQLException;
    List getCarForPassedDays(int days) throws SQLException;
    List getCarByRegion(String region) throws SQLException;
    List getCarByBrand(String brand) throws SQLException;
    Boolean isCarExists(Car car) throws SQLException;

}
