package org.example;

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

        User User = new User();
        User.setName("John Doe");
        User.setEmail("john@example.com");
        User.setAge(30);
        userDao.insert(User);

        System.out.println("User count: " + userDao.getUserCount());

        userDao.insertUserUsingSimpleJdbcInsert("Jane Doe", "jane@example.com", 25);

        System.out.println("User count: " + userDao.getUserCount());

        List<User> users = userDao.getUserGreaterThanAge(25);

        System.out.println(userDao.getUserById(2));

        // Using a stream to process the list
        users.stream().forEach(user -> System.out.println("User greater than 25: " + user));

        List<User> users2 = userDao.getAllUsers();

        // Using a stream to process the list
        users2.stream().forEach(user -> System.out.println(user));
    }
}
