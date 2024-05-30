package com.example.demo.service;


import com.example.demo.dto.user.UserInfo;
import com.example.demo.dto.user.UserRegisterRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.entities.Users;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private HikariDataSource dataSource;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }


    //Oracle PL/SQL prosedürleri çağır
    public void registerUser(UserRegisterRequest userRegisterRequest) {
        String sql = "{ call RegisterPackage.RegisterUser(?, ?, ?, ?, ?, ?) }";
        jdbcTemplate.update(sql, userRegisterRequest.getUsername(), userRegisterRequest.getUserPassword(),userRegisterRequest.getFirstname(),userRegisterRequest.getLastname(),userRegisterRequest.getEmail(),userRegisterRequest.getPhone());
    }

    public UserInfo loginUser(String email, String password) {
        UserInfo userInfo = new UserInfo();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "{ call LoginPackage.LoginUser(?, ?, ?, ?, ?) }";
            try (CallableStatement callableStatement = connection.prepareCall(sql)) {
                callableStatement.setString(1, email);
                callableStatement.setString(2, password);
                callableStatement.registerOutParameter(3, Types.NUMERIC);
                callableStatement.registerOutParameter(4, Types.VARCHAR);
                callableStatement.registerOutParameter(5, Types.NUMERIC);
                callableStatement.execute();
                int loginResult = callableStatement.getInt(5);
                if (loginResult == 1) {
                    userInfo.setUserId(callableStatement.getInt(3));
                    userInfo.setEmail(callableStatement.getString(4));
                } else {
                    throw new RuntimeException(callableStatement.getString(4));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to login: " + e.getMessage(), e);
        }
        return userInfo;
    }



    public void resetPassword(String email, String oldPassword, String newPassword) {
        String sql = "{ call ChangePasswordPackage.resetPassword(?, ?, ?) }";
        jdbcTemplate.update(sql, email, oldPassword, newPassword);
    }

}
