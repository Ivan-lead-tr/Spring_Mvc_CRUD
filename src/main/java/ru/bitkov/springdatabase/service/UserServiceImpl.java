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
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);

    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {

        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {

        userDao.deleteUser(id);
    }
}
