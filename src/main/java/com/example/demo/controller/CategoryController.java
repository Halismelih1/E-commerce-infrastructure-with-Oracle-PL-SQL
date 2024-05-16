package com.example.demo.controller;

import com.example.demo.dto.AddCategoryRequest;
import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.entities.Categories;
import com.example.demo.entities.SubCategories;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Categories> getAllCategories() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/categories/{catId}")
    public ResponseEntity<Categories> getCatById(@PathVariable Integer catId) {
        Optional<Categories> categories = categoryService.getCategoryById(catId);
        return categories.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/addCategory")
    public ResponseEntity<String> registerUser(@RequestBody AddCategoryRequest addCategoryRequest) {
        try {
            categoryService.callAddCategoryProcedure(addCategoryRequest.getCategoryName(),addCategoryRequest.getCategoryDesc());
            return new ResponseEntity<>("Category Added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Added Category: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
