package com.example.demo.controller;

import com.example.demo.entities.Categories;
import com.example.demo.entities.SubCategories;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
