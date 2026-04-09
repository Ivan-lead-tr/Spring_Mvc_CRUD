package ru.bitkov.springdatabase.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.bitkov.springdatabase.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery(
                "select u from User u",
                User.class).getResultList();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public void deleteUser(Long id) {

        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
