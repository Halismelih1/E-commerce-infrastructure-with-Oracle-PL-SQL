package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Carts")
public class Carts {
    @Id
    @Column(name = "CARTID")
    private Integer cartId;
    @Column(name = "TOTALAMOUNT")
    private String totalAmount;
    @Column(name = "USERID")
    private String userId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "USERID", referencedColumnName = "USERID", insertable = false, updatable = false)
    private Users user;

}
