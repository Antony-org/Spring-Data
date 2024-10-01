package org.example;

import org.example.daos.UserDAOImp;
import org.example.daos.UserDao;
import org.example.model.User;
import org.example.system.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        UserDao userDao = context.getBean(UserDao.class);
        UserDAOImp userDAOImp = context.getBean(UserDAOImp.class);

        User User = new User();
        User.setName("John Doe");
        User.setEmail("john@example.com");
        User.setAge(30);

        userDao.saveUser(User);

        userDAOImp.save(User);
    }
}
