package com.example.demo.controller;


import com.example.demo.dto.subCategory.AddSubRequest;
import com.example.demo.dto.subCategory.UpdateSubRequest;
import com.example.demo.entities.SubCategories;
import com.example.demo.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<SubCategories> getSubCatById(@PathVariable Integer subId) {
        Optional<SubCategories> subCat = subCategoryService.getSubCategoryById(subId);
        return subCat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addSubCategory")
    public ResponseEntity<String> addSubCategory(@RequestBody AddSubRequest addSubRequest) {
        try {
            subCategoryService.callAddSubCategoryProcedure(addSubRequest.getSubCategoryName(),addSubRequest.getCategoryId());
            return new ResponseEntity<>("subCategory Added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Added subCategory: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deleteSubCategory/{id}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable Integer id) {
        try {
            subCategoryService.callDeleteSubCategoryProcedure(id);
            return new ResponseEntity<>("subCategory deleted successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete subCategory: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateSubCategory")
    public ResponseEntity<String> updateSubCategory(@RequestBody UpdateSubRequest updateSubRequest) {
        try {
            subCategoryService.callUpdateSubCategoryProcedure(updateSubRequest.getSubCategoryId(),updateSubRequest.getSubCategoryName(),updateSubRequest.getCategoryId());
            return new ResponseEntity<>("subCategory updated successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update subCategory: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
