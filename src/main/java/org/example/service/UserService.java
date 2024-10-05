package org.example.service;

import org.example.daos.UserDao;
import org.example.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class UserService {
    String currentTransactionName = TransactionSynchronizationManager.getCurrentTransactionName();

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testTransaction() {
        System.out.println("test transaction");
        System.out.println("Transaction name: " + TransactionSynchronizationManager.getCurrentTransactionName());

        User user = new User();
        user.setName("tested user");
        user.setEmail("john@example.com");
        user.setAge(30);


        User foundUser = userDao.getUserById(57);

        userDao.saveUser(user);

        foundUser.setName("nested transaction user");
        try {
            userDao.updateUser(foundUser);
        } catch (Exception e) {
            System.out.println("Exception in nested transaction: " + e.getMessage());
        }
        userDao.saveUser(user);

        userDao.getUserById(user.getId());

        System.out.println("test transaction end");
    }
}
