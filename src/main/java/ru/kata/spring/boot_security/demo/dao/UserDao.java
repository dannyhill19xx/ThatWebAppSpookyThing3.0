package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(int id);

    void saveUser(User user);

    void updateUser(User updatedUser);

    void deleteUser(int id);

    User findUserByUsername(String username);


}