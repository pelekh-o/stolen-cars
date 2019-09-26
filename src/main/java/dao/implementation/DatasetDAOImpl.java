package dao.implementation;

import dao.DatasetDAO;
import entity.Dataset;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

import java.sql.SQLException;
import java.util.Collection;

public class DatasetDAOImpl implements DatasetDAO {
    private static final Logger log = Logger.getLogger(DatasetDAOImpl.class);

    @Override
    public void addDataset(Dataset dataset) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(dataset);
        transaction.commit();
        session.close();
    }

    @Override
    public Dataset getDatasetById(Integer id) throws SQLException {
        return HibernateUtil.getSessionFactory().openSession().get(Dataset.class, id);
    }

    @Override
    public Collection getAllDataset() throws SQLException {
        return HibernateUtil.getSessionFactory().openSession().createQuery("from Dataset").list();
    }

    @Override
    public void updateDataset(Dataset dataset) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("update Dataset d set d.updated = :updated where d.id = :id")
                .setParameter("updated", dataset.getUpdated())
                .setParameter("id", dataset.getDatasetid()).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public Dataset getLastUpdate() {
        return (Dataset) HibernateUtil.getSessionFactory().openSession()
                .createQuery("from Dataset order by updated DESC").setMaxResults(1).uniqueResult();
    }
}
