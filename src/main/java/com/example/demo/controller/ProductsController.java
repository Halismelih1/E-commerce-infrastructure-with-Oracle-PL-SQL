package com.example.demo.controller;

import com.example.demo.dto.category.AddCategoryRequest;
import com.example.demo.dto.category.UpdateCategoryRequest;
import com.example.demo.dto.product.*;
import com.example.demo.entities.Products;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Products> getAllProd() {
        return productService.getAllProd();
    }

    @GetMapping("/products/{prodId}")
    public ResponseEntity<Products> getSubCatById(@PathVariable Integer prodId) {
        Optional<Products> products = productService.getProdById(prodId);
        return products.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody AddProductRequest addProductRequest) {
        try {
            productService.callAddProductProcedure(addProductRequest.getProductName(),addProductRequest.getProductPrice(),addProductRequest.getProductDesc(),addProductRequest.getSubCategoryId());
            return new ResponseEntity<>("Product Added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Added Product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        try {
            productService.callDeleteProductProcedure(id);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete Product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
        try {
            productService.callUpdateProductProcedure(updateProductRequest.getProductId(),updateProductRequest.getProductName(),updateProductRequest.getProductPrice(),updateProductRequest.getProductDesc(),updateProductRequest.getSubCategoryId());
            return new ResponseEntity<>("Product updated successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update Product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/filter")
    public List<FilterProdRes> filterProducts(@RequestBody FilterProdReq filterProdReq) {
        return productService.callFilterProdProcedure(
                filterProdReq.getSubCategoryId(),
                filterProdReq.getMinPrice(),
                filterProdReq.getMaxPrice(),
                filterProdReq.getProductName()
        );
    }

    @GetMapping("/products/filterByCategory/{id}")
    public List<FilterProdByCategory> filterProductsByCat(@PathVariable int id) {
        return productService.callFilterProdByCategoryProcedure(id);
    }

}
