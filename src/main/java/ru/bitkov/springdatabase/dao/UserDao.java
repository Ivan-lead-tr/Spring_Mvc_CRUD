package ru.bitkov.springdatabase.dao;

import ru.bitkov.springdatabase.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);

}
