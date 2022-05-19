package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ApplicationContext context;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, ApplicationContext context, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.context = context;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        setEncryptedPassword(user);
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void removeUser(long id) {
        userDao.removeUser(id);
    }

    @Transactional
    @Override
    public void editUser(User user) {
        setEncryptedPassword(user);
        userDao.editUser(user);
    }

    @Override
    public void setEncryptedPassword(User user) {
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
