package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    User getUserById(int id);

    void saveUser(User user);

    void updateUser(User updatedUser);

    void deleteUser(int id);

    User findUserByUsername(String username);

    void setEncryptedPassword(User user);
}