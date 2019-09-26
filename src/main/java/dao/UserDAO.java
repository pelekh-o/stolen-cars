package dao;

import entity.User;

import java.util.Collection;

public interface UserDAO {
    void addUser(User user);
    void updateUser(User user);
    User getUserById(Integer userId);
    User getUserByTelegramId(Integer userId);
    Collection getUsersByChatId(Long chatId);
    Collection getRememberedUsers();
    Collection getAllUsers();
    void deleteUser(User user);
}
