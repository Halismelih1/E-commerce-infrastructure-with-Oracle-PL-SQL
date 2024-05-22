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

    public SubCategoryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SubCategories> getAllSubCategory() {
        return subCategoryRepository.findAll();
    }

    public Optional<SubCategories> getSubCategoryById(Integer subId) {
        return subCategoryRepository.findById(subId);
    }

    //Oracle PL/SQL prosedürleri çağır

    public void callAddSubCategoryProcedure(String subCategoryName, Integer categoryId) {
        String sql = "{call SUBCATEGORYCRUD.addSubCategory(?, ?)}";
        jdbcTemplate.update(sql, subCategoryName, categoryId);

    }

    public void callDeleteSubCategoryProcedure(int id) {
        String sql = "{call SUBCATEGORYCRUD.deleteSubCategory(?)}";
        jdbcTemplate.update(sql, id);

    }

    public void callUpdateSubCategoryProcedure(int id, String subCategoryName, Integer categoryId) {
        String sql = "{call SUBCATEGORYCRUD.updateSubCategory(?,?,?)}";
        jdbcTemplate.update(sql, id,subCategoryName , categoryId);

    }
}
