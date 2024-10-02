package org.example;

import org.example.daos.UserDao;
import org.example.model.User;
import org.example.service.UserService;
import org.example.system.AppConfig;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        UserDao userDao = context.getBean(UserDao.class);

        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setAge(51);


        userDao.saveUser(user);




//        List<User> users = userDao.getAllUsers();
//        for (User user : users) {
//            System.out.println(user);
//        }

        //userDAOImp.save(User);
    }
}
