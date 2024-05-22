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

    public CategoryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Categories> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Optional<Categories> getCategoryById(Integer catId) {
        return categoryRepository.findById(catId);
    }
    //Oracle PL/SQL prosedürleri çağır
    public void callAddCategoryProcedure(String categoryName) {
        String sql = "{call categoryCrud.addCategory(?)}";
        jdbcTemplate.update(sql, categoryName);

    }

    public void callDeleteCategoryProcedure(int id) {
        String sql = "{call categoryCrud.deleteCategory(?)}";
        jdbcTemplate.update(sql, id);

    }

    public void callUpdateCategoryProcedure(int id, String categoryName) {
        String sql = "{call categoryCrud.updateCategory(?,?)}";
        jdbcTemplate.update(sql, id,categoryName);

    }


}
