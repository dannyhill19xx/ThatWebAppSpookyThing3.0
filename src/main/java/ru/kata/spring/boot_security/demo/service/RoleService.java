package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles(Set<String> roles);

    List<User> getAllUserRoles();
    void save(Role role);

}
