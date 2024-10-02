package org.example.daos;


import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;


    @Transactional
    public void saveUser(User user) {
        System.out.println("EntityManager in Transaction: 1 " + entityManager);
        System.out.println("Actual EntityManager in Transaction 1: " + entityManager.getDelegate());

        entityManager.persist(user);
        System.out.println(entityManager.isOpen());

        System.out.println("User saved successfully with id: " + user.getId());
    }

    @Transactional
    public void updateUser(User user) {
        user.setName("Updated Name");
        System.out.println("EntityManager in Transaction 2: " + entityManager);
        System.out.println("Actual EntityManager in Transaction 2: " + entityManager.getDelegate());

        entityManager.merge(user);
        System.out.println("User updated successfully with id: " + user.getId());
    }

    //@Transactional(propagation = Propagation.MANDATORY)
    //Never returns an error
//    @Transactional(propagation = Propagation.NESTED)
//    public void updateUser(User user) {
//        if (user.getId() == 51) {
//            throw new RuntimeException("Simulating error in nested transaction");
//        }
//        hibernateTemplate.update(user);
//        System.out.println("User updated successfully with id: " + user.getId());
//    }
//
//    //@Transactional
//    public User getUserById(int id) {
//        return hibernateTemplate.get(User.class, id);
//    }
//
//    public List<User> getAllUsers() {
//        return hibernateTemplate.loadAll(User.class);
//    }
}