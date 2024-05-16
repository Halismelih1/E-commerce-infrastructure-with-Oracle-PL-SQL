package com.example.demo.service;

import com.example.demo.entities.Products;
import com.example.demo.entities.SubCategories;
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

}
