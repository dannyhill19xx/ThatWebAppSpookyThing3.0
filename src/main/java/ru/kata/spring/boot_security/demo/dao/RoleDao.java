package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    Role getRoleById(long id);

    Set<Role> getAllRoles(Set<String> roles);

    List<User> getAllUserRoles();

    void save(Role role);

    void update(Role updatedRole);

    void removeRole(long id);

}
