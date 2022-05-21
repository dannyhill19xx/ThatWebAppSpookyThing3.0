package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT user FROM User user ", User.class).getResultList();
    }
    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public void updateUser(User updatedUser) {
        entityManager.merge(updatedUser);
    }
    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUserById(id));
    }
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }
    @Override
    public User findUserByUsername(String username) {
        return (User) entityManager.createQuery("select user from User user where user.email = ?1")
                .setParameter(1, username)
                .getSingleResult();
    }
}
