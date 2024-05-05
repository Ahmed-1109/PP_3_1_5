package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {
    private final UserService userService;
    private final RoleService roleService;

    public DataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userService.getUsers().isEmpty()) {

            Role roleAdmin = new Role("ROLE_ADMIN");
            roleService.save(roleAdmin);
            userService.add(new User("Иван", "Иванов", "admin@mail.ru", "admin", Set.of(roleAdmin)));

            Role roleUser = new Role("ROLE_USER");
            roleService.save(roleUser);
            userService.add(new User("Петр", "Петров", "user@mail.ru", "user", Set.of(roleUser)));
        }
    }
}
