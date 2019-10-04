package dao.implementation;

import dao.CarInfoDAO;
import entity.Car;
import entity.CarInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class CarInfoDAOImpl implements CarInfoDAO {
    @Override
    public void addCar(CarInfo carInfo) throws SQLException {
        if (!isCarInfoExists(carInfo)) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(carInfo);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public CarInfo getCarById(Integer id) throws SQLException {
        return HibernateUtil.getSessionFactory().openSession().get(CarInfo.class, id);
    }

    @Override
    public List getAllCars() throws SQLException {
        return HibernateUtil.getSessionFactory().openSession().createQuery("from CarInfo ").list();

    }

    @Override
    public List getCarByVehicleNumber(String vehicleNumber) throws SQLException {
        return HibernateUtil.getSessionFactory().openSession()
                .createQuery("select c from CarInfo c where c.vehicleNumber = :vehicleNumber order by c.regdate desc")
                .setParameter("vehicleNumber", vehicleNumber)
                .list();
    }

    public Boolean isCarInfoExists(CarInfo carInfo) throws SQLException {
        return !getCarByVehicleNumber(carInfo.getVehicleNumber()).isEmpty();
    }

}
