package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(long id, User editedUser) {
        editedUser.setId(id);
        entityManager.merge(editedUser);
    }

    @Override
    public void removeUser(long id) {
        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        return (User) entityManager.createQuery("select user from User user where user.username = :username")
                .setParameter("username", username).getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select user from User user", User.class)
                .getResultList();
    }
}
