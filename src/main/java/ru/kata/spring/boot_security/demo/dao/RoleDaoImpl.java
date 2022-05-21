package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllRoles() {
        return entityManager.createQuery("FROM Role").getResultList();
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
    @Override
    public Set<Role> getRolesSet(Set<String> roles) {
        return new HashSet<>(entityManager.createQuery("select role from Role role where role.roleName = :role", Role.class)
                .setParameter("role", roles)
                .getResultList());
    }
}