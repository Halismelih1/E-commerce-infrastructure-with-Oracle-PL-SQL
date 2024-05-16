package com.example.demo.service;

import com.example.demo.entities.SubCategories;
import com.example.demo.entities.Users;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    private JdbcTemplate jdbcTemplate;

    public List<SubCategories> getAllSubCategory() {
        return subCategoryRepository.findAll();
    }

    public Optional<SubCategories> getSubCategoryById(Integer subId) {
        return subCategoryRepository.findById(subId);
    }
}
