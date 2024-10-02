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

    public void saveUser(User user) {
        userDao.saveUser(user);
        System.out.println("User saved successfully with id: " + user.getId());
    }

//    public User getUserById(int id) {
//        userDao.getUserById(id);
//        return new User(id, "John", "johndoe", 25);
//    }
//
//    public void updateUser(User user) {
//        userDao.updateUser(user);
//        System.out.println("User updated successfully with id: " + user.getId());
//    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void testTransaction() {
//        System.out.println("test transaction");
//
//        User user = new User();
//        user.setName("tested user");
//        user.setEmail("john@example.com");
//        user.setAge(30);
//
//
//        User foundUser = userDao.getUserById(51);
//
//        userDao.saveUser(user);
//
//        foundUser.setName("nested transaction user");
//        try {
//            userDao.updateUser(foundUser);
//        } catch (Exception e) {
//            System.out.println("Exception in nested transaction: " + e.getMessage());
//        }
//        userDao.saveUser(user);
//
//        userDao.getUserById(user.getId());
//
//
//
//        System.out.println("test transaction end");
//    }
}
