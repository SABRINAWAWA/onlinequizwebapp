package com.onlinequizwebapp.onlinequizwebapp.dao.implementations;

import com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper.UserRowMapper;
import com.onlinequizwebapp.onlinequizwebapp.dao.interfaces.UserDAO;
import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO {

    JdbcTemplate jdbcTemplate;
    UserRowMapper rowMapper;

    @Autowired
    public UserDAOImpl(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void createNewUser(User user) {
        String query = "INSERT INTO users (email, password, firstname, lastname, isActive, isAdmin) values (?, ?, ?, ?, true, false)";
        jdbcTemplate.update(query, user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName());
    }

    @Override
    public User activeUserById(Integer id) {
        String query = "UPDATE users SET isActive=true WHERE userId=?";
        jdbcTemplate.update(query, id);
        return getUserById(id);
    }

    @Override
    public User suspendedUserById(Integer id) {
        String query = "UPDATE users SET isActive=false WHERE userId=?";
        jdbcTemplate.update(query, id);
        return getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(query, rowMapper);
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        String query = "SELECT * FROM users WHERE userId=?";
        List<User> user = jdbcTemplate.query(query, rowMapper, id);
        return user.size()==0 ? null:user.get(0);
    }

    @Override
    public List<User> getUserWhoTookQuiz(){
        String query="SELECT distinct u.userId, u.password, u.email, u.firstname,u.lastname,u.isActive, u.isAdmin FROM Users u\n" +
                "RIGHT JOIN \n" +
                "Quiz q\n" +
                "ON u.userId=q.userId \n" +
                "ORDER BY u.firstname ASC;";
        List<User> users = jdbcTemplate.query(query, rowMapper);
        return users;
    }
}
