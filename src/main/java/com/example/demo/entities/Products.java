package com.example.demo.entities;

import com.example.demo.service.SubCategoryService;
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
    @Column(name = "SUBCATEGORYID")
    private String subCategoryId;

    @ManyToOne
    @JoinColumn(name = "SUBCATEGORYID", insertable = false, updatable = false)
    private SubCategories subCategory;



}
