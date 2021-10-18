package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void updateUser(long id, User user);

    void removeUser(long id);

    User getUserById(long id);

    User getUserByUsername(String s);

    List<User> getAllUsers();
}
