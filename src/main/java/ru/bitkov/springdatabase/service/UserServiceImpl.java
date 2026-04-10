package ru.bitkov.springdatabase.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bitkov.springdatabase.dao.UserDao;
import ru.bitkov.springdatabase.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder passwordEncoder){
        this.userDao=userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void saveUser (String firstName, String lastName,
                          String email, Byte age, String password) {
        User user = new User(firstName, lastName, email, age);
        user.setPassword(passwordEncoder.encode(password));
        userDao.saveUser(user);

    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {

        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void updateUser(Long id, String firstName, String lastName,
                           String email, Byte age, String password) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAge(age);
        user.setPassword(passwordEncoder.encode(password));
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {

        userDao.deleteUser(id);
    }
}
