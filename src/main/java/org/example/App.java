package org.example;

import org.example.daos.UserDAOImp;
import org.example.daos.UserDao;
import org.example.daos.UserDaoTask1;
import org.example.daos.UserDaoTask2;
import org.example.model.User;
import org.example.service.UserService;
import org.example.system.AppConfig;
import org.hibernate.SessionFactory;
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

        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setAge(51);

        UserService userService = context.getBean(UserService.class);
        userService.testTransaction();

        UserDaoTask1 userDaoTask1 = context.getBean(UserDaoTask1.class);

        userDaoTask1.setSessionFactory(context.getBean(SessionFactory.class));
        userDaoTask1.saveUser(user);

        UserDaoTask2 userDaoTask2 = context.getBean(UserDaoTask2.class);
        user.setName("hello task 2");
        userDaoTask2.updateUser(user);



//        List<User> users = userDao.getAllUsers();
//        for (User user : users) {
//            System.out.println(user);
//        }

        //userDAOImp.save(User);
    }
}
