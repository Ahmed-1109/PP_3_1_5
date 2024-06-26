package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleRepository {
    Role getRoleByName(String name);

    List<Role> getRoles();

    void save(Role role);
}
