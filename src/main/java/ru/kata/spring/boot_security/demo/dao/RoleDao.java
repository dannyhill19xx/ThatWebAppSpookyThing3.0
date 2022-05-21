package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<User> getAllRoles();
    void saveRole(Role role);
    Set<Role> getRolesSet(Set<String> roles);
}