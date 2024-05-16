package com.example.demo.controller;

import com.example.demo.entities.SubCategories;
import com.example.demo.entities.Users;
import com.example.demo.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/subCategories")
    public List<SubCategories> getAllSubCategories() {
        return subCategoryService.getAllSubCategory();
    }

    @GetMapping("/subCategories/{subId}")
    public ResponseEntity<SubCategories> getUserById(@PathVariable Integer subId) {
        Optional<SubCategories> subCat = subCategoryService.getSubCategoryById(subId);
        return subCat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
