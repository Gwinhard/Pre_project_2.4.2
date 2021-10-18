package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void updateUser(long id, User user);

    void removeUser(long id);

    User getUserById(long id);

    User getUserByUsername(String username);

    List<User> getAllUsers();
}
