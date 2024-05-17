package com.example.demo.service;

import com.example.demo.entities.SubCategories;
import com.example.demo.repository.SubCategoryRepository;
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

    //Oracle PL/SQL prosedürleri çağır

    public void callAddSubCategoryProcedure(String subCategoryName, String subCategoryDesc, Integer categoryId) {
        String sql = "{call subCategoryCrud.addSubCategory(?, ?, ?)}";
        jdbcTemplate.update(sql, subCategoryName, subCategoryDesc, categoryId);

    }

    public void callDeleteSubCategoryProcedure(int id) {
        String sql = "{call subCategoryCrud.deleteSubCategory(?)}";
        jdbcTemplate.update(sql, id);

    }

    public void callUpdateSubCategoryProcedure(int id, String subCategoryName, String subCategoryDesc, Integer categoryId) {
        String sql = "{call categoryCrud.updateCategory(?,?,?,?)}";
        jdbcTemplate.update(sql, id,subCategoryName, subCategoryDesc , categoryId);

    }
}
