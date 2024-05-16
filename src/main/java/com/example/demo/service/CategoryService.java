package com.example.demo.service;

import com.example.demo.entities.Categories;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    private JdbcTemplate jdbcTemplate;

    public List<Categories> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Optional<Categories> getCategoryById(Integer catId) {
        return categoryRepository.findById(catId);
    }
}
