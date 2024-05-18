package com.example.demo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterRequest {
    private String username;
    private String userPassword;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
}
