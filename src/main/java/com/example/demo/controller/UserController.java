package com.example.demo.controller;

import com.example.demo.dto.user.ChangePasswordRequest;
import com.example.demo.dto.user.UserInfo;
import com.example.demo.dto.user.UserRegisterRequest;
import com.example.demo.entities.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer userId) {
        Optional<Users> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        try {
            userService.registerUser(userRegisterRequest);
            return new ResponseEntity<>("User registered successfully:"  + userRegisterRequest.getEmail() +  "ile Yeni Kayıt Oluşturuldu", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<UserInfo> loginUser(@PathVariable String email, @PathVariable String password) {
        try {
            UserInfo userInfo = userService.loginUser(email, password);
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/changepassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        try {
            userService.resetPassword(changePasswordRequest.getEmail(),
                    changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword());
            return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to change password: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

