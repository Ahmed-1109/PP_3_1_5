package ru.kata.spring.boot_security.demo.models;


import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Role> roles;
}
