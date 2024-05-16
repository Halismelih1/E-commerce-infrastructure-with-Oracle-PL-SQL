package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Users")
public class Users {
    @Id
    @Column(name = "USERID")
    private Integer userId;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "USERPASSWORD")
    private String userPassword;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;

}

