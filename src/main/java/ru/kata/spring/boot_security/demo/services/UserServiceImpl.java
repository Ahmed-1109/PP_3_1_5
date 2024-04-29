package ru.kata.spring.boot_security.demo.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.models.UserDTO;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;


    @Transactional
    @Override
    public void addUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        if (userDTO.getRoles().isEmpty()) {
            Role roleUser = roleRepository.getRoleByName("ROLE_USER");
            user.setRoles(Set.of(roleUser));
        }
        userRepository.addUser(user);
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        userRepository.removeUser(id);
    }

    @Transactional
    @Override
    public void updateUser(UserDTO userDTO) {
        User oldUser = userRepository.getUserById(userDTO.getId());
        User updatedUser = modelMapper.map(userDTO, User.class);
        if (!oldUser.getPassword().equals(userDTO.getPassword())) {
            updatedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        if (userDTO.getRoles().isEmpty()) {
            Role roleUser = roleRepository.getRoleByName("ROLE_USER");
            updatedUser.setRoles(Set.of(roleUser));
        }
        userRepository.updateUser(updatedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        User user = userRepository.getUserById(id);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUserName(String email) {
       return userRepository.findByUserName(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByUserName(email);
    }
}
