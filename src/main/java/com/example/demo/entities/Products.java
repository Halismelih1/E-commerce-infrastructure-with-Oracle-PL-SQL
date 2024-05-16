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
@Table(name = "Products")
public class Products {
    @Id
    @Column(name = "PRODUCTID")
    private Integer productId;
    @Column(name = "PRODUCTNAME")
    private String productName;
    @Column(name = "PRODUCTPRICE")
    private String productPrice;
    @Column(name = "PRODUCTDESC")
    private String productDesc;
    @Column(name = "CATEGORYID")
    private String categoryId;
    @Column(name = "SUBCATEGORYID")
    private String subCategoryId;
}
