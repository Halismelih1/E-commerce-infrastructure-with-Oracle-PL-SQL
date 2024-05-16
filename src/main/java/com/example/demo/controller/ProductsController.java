package com.example.demo.controller;

import com.example.demo.entities.Products;
import com.example.demo.entities.SubCategories;
import com.example.demo.service.ProductService;
import com.example.demo.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Products> getAllProd() {
        return productService.getAllProd();
    }

    @GetMapping("/products/{prodId}")
    public ResponseEntity<Products> getSubCatById(@PathVariable Integer prodId) {
        Optional<Products> products = productService.getProdById(prodId);
        return products.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
