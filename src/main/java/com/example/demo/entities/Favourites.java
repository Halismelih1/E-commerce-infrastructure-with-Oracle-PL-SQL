package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Favourites")public class Favourites {
    @Id
    @Column(name = "FAVOURITEID")
    private Integer favouriteId;
    @Column(name = "PRODUCTID")
    private String productId;
    @Column(name = "USERID")
    private String userId;
}
