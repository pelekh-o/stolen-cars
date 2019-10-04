package persistence;

import entity.Car;
import entity.CarInfo;
import entity.Dataset;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import static services.BotConfig.DB_PASSWORD;
import static services.BotConfig.DB_USER;

public class HibernateUtil {
    private static Logger logger = Logger.getLogger(HibernateUtil.class.getName());


    private static final SessionFactory sessionFactory;

    static {

        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.username", DB_USER);
            configuration.setProperty("hibernate.connection.password", DB_PASSWORD);
            configuration.addAnnotatedClass(Dataset.class);
            configuration.addAnnotatedClass(Car.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(CarInfo.class);
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (ExceptionInInitializerError ex) {
            logger.error(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Gets hibernate session factory that was initialized at application startup.
     *
     * @return hibernate session factory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}