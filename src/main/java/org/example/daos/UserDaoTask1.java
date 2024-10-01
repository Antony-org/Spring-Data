package org.example.daos;

import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoTask1 {

    private Session session;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    public void saveUser(User user) {
        session.save(user);
        System.out.println("User saved successfully with id: " + user.getId());
    }

    public void close() {
        session.close();
    }
}
