package dao.implementation;

import dao.CarDAO;
import entity.Car;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    private static final Logger log = Logger.getLogger(CarDAOImpl.class);

    @Override
    public void addCar(Car car) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }

    @Override
    public List getAllCars() {
        return HibernateUtil.getSessionFactory().openSession().createQuery("from Car").list();
    }

    @Override
    public Car getCarById(Integer id) {
        return HibernateUtil.getSessionFactory().openSession().get(Car.class, id);
    }

    @Override
    public List getCarByVehicleNumber(String vehicleNumber) {
        return HibernateUtil.getSessionFactory().openSession()
                .createQuery("select c from Car c where c.VEHICLENUMBER LIKE :vehicleNumber")
                .setParameter("vehicleNumber", "%" +  vehicleNumber + "%")
                .list();
    }

    @Override
    public List getCarByTheftDate(String theftDate) {
        return HibernateUtil.getSessionFactory().openSession()
                .createQuery("select c from Car c where c.THEFT_DATA LIKE :theftDate")
                .setParameter("theftDate", theftDate + "%")
                .list();
    }

    @Override
    public List getCarByInsertDate(Date insertDate) {
        return HibernateUtil.getSessionFactory().openSession()
                .createQuery("select c from Car c where c.THEFT_DATA LIKE :insertDate")
                .setParameter("insertDate", insertDate + "%")
                .list();
    }

    @Override
    public List getCarForPassedDays(int days) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(sdf.format(date));

        return HibernateUtil.getSessionFactory().openSession()
                .createQuery("select c from Car c where c.THEFT_DATA >= :date")
                .setParameter("date", date)
                .list();
    }

    @Override
    public List getCarByRegion(String region) {
        return HibernateUtil.getSessionFactory().openSession()
                .createQuery("select c from Car c where c.OVD = :region")
                .setParameter("region", region)
                .list();
    }

    @Override
    public List getCarByBrand(String brand) {
        return HibernateUtil.getSessionFactory().openSession()
                .createQuery("select c from Car c where c.BRAND LIKE :brand")
                .setParameter(brand, brand)
                .list();
    }

    public Boolean isCarExists(Car car) {
        return HibernateUtil.getSessionFactory().openSession()
                .get(Car.class, car.getCAR_ID()) != null;
    }
}
