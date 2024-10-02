package org.example.daos;


import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveUser(User user) {
        hibernateTemplate.save(user);
        System.out.println("User saved successfully with id: " + user.getId());
    }

    //@Transactional(propagation = Propagation.MANDATORY)
    //Never returns an error
    @Transactional(propagation = Propagation.NESTED)
    public void updateUser(User user) {
        user.setName("Nested Transaction User");
//        if (user.getId() == 51) {
//            throw new RuntimeException("Simulating error in nested transaction");
//        }
        hibernateTemplate.update(user);
        System.out.println("User updated successfully with id: " + user.getId());
    }

    //@Transactional
    public User getUserById(int id) {
        return hibernateTemplate.get(User.class, id);
    }

    public List<User> getAllUsers() {
        return hibernateTemplate.loadAll(User.class);
    }
}