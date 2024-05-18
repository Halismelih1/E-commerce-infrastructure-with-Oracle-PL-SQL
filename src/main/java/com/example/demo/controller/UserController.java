package com.example.demo.controller;

import com.example.demo.dto.ChangePasswordRequest;
import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.entities.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    //login şuan için çalışmıyor bakılacak
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        try {
            boolean loginResult = userService.loginUser(email, password);
            if (loginResult) {
                return new ResponseEntity<>("Login successful: " + email + " ile giriş yapıldı.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to login: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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

