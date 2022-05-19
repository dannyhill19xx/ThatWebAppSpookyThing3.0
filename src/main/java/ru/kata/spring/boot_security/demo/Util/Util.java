package ru.kata.spring.boot_security.demo.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;


@Component
public class Util implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Util(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {

        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleService.save(role1);
        roleService.save(role2);

        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();
        Set<Role> roleUserAdmin = new HashSet<>();

        roleAdmin.add(role1);
        roleUser.add(role2);
        roleUserAdmin.add(role1);
        roleUserAdmin.add(role2);


        User user1 = new User("admin", "administrator", 23, "admin@mail.ru", "admin", roleAdmin);
        User user2 = new User("user", "niceuser", 27, "user@mail.ru", "user", roleUser);
        User user3 = new User("Danny", "Hill", 24, "dannyhill@mail.ru", "1234", roleUserAdmin);


        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);

    }
}

    