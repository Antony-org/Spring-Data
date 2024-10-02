package org.example.service;

import org.example.daos.UserDao;
import org.example.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void test(User user) {
        userDao.saveUser(user);
        userDao.updateUser(user);
        System.out.println("User saved successfully with id: " + user.getId());
    }


}