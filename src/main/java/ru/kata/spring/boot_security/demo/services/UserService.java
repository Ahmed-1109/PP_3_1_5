package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.models.dto.UserDTO;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(UserDTO userDTO);

    void add(User user);

    List<User> getUsers();

    UserDTO getUserById(Long id);

    void removeUser(Long id);

    void updateUser(UserDTO userDTO);

    User findByUserName(String email);
}
