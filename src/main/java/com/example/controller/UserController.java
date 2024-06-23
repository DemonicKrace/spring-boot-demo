package com.example.controller;

import com.example.user_login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

    @PostMapping("/register")
    // hash password
    public String register(@RequestBody User user) {
        String sql = "INSERT INTO users(email, name, password) VALUES (:userEmail, :userName,:userPassword)";
        Map<String, Object> map = new HashMap<>();
        map.put("userEmail", user.getEmail());
        map.put("userName", user.getName());
        map.put("userPassword", user.getPassword());

        namedParameterJdbcTemplate.update(sql, map);
        return "user created";
    }

    @PostMapping("/login")
    public String login(@RequestBody User pendingUser) {
        String sql = "SELECT * FROM users WHERE email = :userEmail";
        Map<String, Object> map = new HashMap<>();
        map.put("userEmail", pendingUser.getEmail());
        String pendingPassword = pendingUser.getPassword();
        User user;
        try {
            user = namedParameterJdbcTemplate.queryForObject(sql, map, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return "invalid input";
        }
        if (pendingPassword.equals(user.getPassword())) {
            return "Valid input";
        }
        return "invalid input";
    }
}
