package com.example.demo.service;

import com.example.demo.entities.Products;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    private JdbcTemplate jdbcTemplate;

    public List<Products> getAllProd() {
        return productRepository.findAll();
    }

    public Optional<Products> getProdById(Integer prodId) {
        return productRepository.findById(prodId);
    }

    //Oracle PL/SQL prosedürleri çağır
    public void callAddProductProcedure(String productName, Integer productPrice, String productDesc,Integer categoryId, Integer subCategoryId) {
        String sql = "{call productCrud.addProduct(?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, productName, productPrice, productDesc, categoryId, subCategoryId);

    }

    public void callDeleteProductProcedure(int id) {
        String sql = "{call productCrud.deleteProduct(?)}";
        jdbcTemplate.update(sql, id);

    }

    public void callUpdateProductProcedure(int id, String productName, Integer productPrice, String productDesc,Integer categoryId, Integer subCategoryId) {
        String sql = "{call productCrud.updateProduct(?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, id,productName, productPrice, productDesc, categoryId, subCategoryId);

    }

}
