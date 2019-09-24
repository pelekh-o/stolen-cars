package dao.implementation;

import dao.UserDAO;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public void addUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User getUserById(Integer userId) {
        return HibernateUtil.getSessionFactory().openSession().get(User.class, userId);
    }

    @Override
    public User getUserByTelegramId(Integer userId) {
        List usersList =  HibernateUtil.getSessionFactory().openSession()
                .createQuery("select u from User u where u.userId = :userId")
                .setParameter("userId", userId)
                .list();
        System.out.println("getUserByTelegramId: " + usersList.size());
        if (usersList.size() == 1)
            return (User) usersList.get(0);;
        return null;
    }

   /* @Override
    public boolean isTelegramUserExistInDB(Integer userId) {
        return HibernateUtil.getSessionFactory().openSession()
                .get(User.class, userId)) != null;
    }*/

    @Override
    public Collection getUsersByChatId(Long chatId) {
        return  HibernateUtil.getSessionFactory().openSession()
                .createQuery("select u from User u where u.chatId = :chatId")
                .setParameter("chatId", chatId)
                .list();
    }

    @Override
    public Collection getRememberedUsers() {
        return  HibernateUtil.getSessionFactory().openSession()
                .createQuery("select u from User u where u.isRemembered = 1")
                .list();
    }

    @Override
    public Collection getAllUsers() {
        return HibernateUtil.getSessionFactory().openSession().createQuery("from User").list();
    }

    @Override
    public void deleteUser(User user)  {
        user.setIsRemembered((byte) 0);
        updateUser(user);
    }
}
