package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u join fetch u.roles", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return Optional.ofNullable(entityManager.createQuery("select u from User u JOIN FETCH u.roles where u.id =:id", User.class)
                        .setParameter("id", id)
                        .getSingleResult())
                .orElseThrow(NoSuchUserException::new);
    }

    @Override
    public void removeUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByUserName(String email) {
        return Optional.ofNullable(entityManager.createQuery("SELECT u from User u  join fetch u.roles WHERE u.email = :email", User.class).
                        setParameter("email", email)
                        .getSingleResult())
                .orElseThrow(NoSuchUserException::new);
    }
}
