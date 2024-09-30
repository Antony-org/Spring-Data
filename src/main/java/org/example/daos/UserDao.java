package org.example.daos;

import org.example.mappers.UserResultSetExtractor;
import org.example.mappers.UserRowMapper;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao extends JdbcDaoSupport {
    
//    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDao(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        setDataSource(dataSource);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingColumns("name", "email", "age")
                .usingGeneratedKeyColumns("id");

    }

    public void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50)," +
                "email VARCHAR(50)," +
                "age INT" +
                ")";

        getJdbcTemplate().execute(sql);
        System.out.println("User table created successfully.");
    }

    public void insertUser(String name, String email, int age) {
        String sql = "INSERT INTO users (name, email, age) VALUES (:name, :email, :age)";

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("age", age);

        namedParameterJdbcTemplate.update(sql, params);
        System.out.println("User inserted: " + name);
    }

    public void insert(User user){
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
        simpleJdbcInsert.execute(parameters);
    }

    //Insert user using SimpleJdbcInsert
    public void insertUserUsingSimpleJdbcInsert(String name, String email, int age) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", name);
        parameters.put("email", email);
        parameters.put("age", age);
        simpleJdbcInsert.execute(parameters);
        System.out.println("User inserted: " + name);
    }

    public int getUserCount() {
        String sql = "SELECT COUNT(*) FROM users";
        return getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    public List<User> getUserGreaterThanAge(int age) {
        String sql = "SELECT * FROM users WHERE age > ?";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(User.class), age);
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return getJdbcTemplate().queryForObject(sql, new UserRowMapper(), id);
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return getJdbcTemplate().query(sql, new UserResultSetExtractor());
    }

}