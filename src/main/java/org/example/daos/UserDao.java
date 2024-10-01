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

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void saveUser(User user) {
        hibernateTemplate.save(user);
        System.out.println("User saved successfully with id: " + user.getId());
    }

    public User getUserById(int id) {
        return hibernateTemplate.get(User.class, id);
    }

    @Transactional
    public void updateUser(User user) {
        hibernateTemplate.update(user);
    }

    public List<User> getAllUsers() {
        return hibernateTemplate.loadAll(User.class);
    }
}