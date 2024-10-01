package org.example.daos;

import org.example.model.User;
import org.hibernate.FlushMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoTask2 extends HibernateDaoSupport {

    UserDaoTask2(HibernateTemplate hibernateTemplate) {
        this.setHibernateTemplate(hibernateTemplate);
    }
    public void saveUser(User user) {
       getHibernateTemplate().execute(new HibernateCallback<User>() {
           @Override
           public User doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
               session.save(user);
               System.out.println("User saved with ID: " + user.getId());
               return user;
           }
       });
    }

    //update
    public void updateUser(User user) {
        getHibernateTemplate().execute(new HibernateCallback<User>() {
            @Override
            public User doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
                session.setFlushMode(FlushMode.COMMIT);
                session.beginTransaction();
                session.update(user);
                session.getTransaction().commit();
                System.out.println("User updated with ID: " + user.getId());
                return user;
            }
        });
    }
}
