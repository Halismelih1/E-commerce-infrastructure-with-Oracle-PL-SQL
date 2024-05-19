package com.example.demo.controller;

import com.example.demo.dto.cart.AddToBasketRequest;
import com.example.demo.dto.cart.RemoveToBasketRequest;
import com.example.demo.dto.category.AddCategoryRequest;
import com.example.demo.dto.category.UpdateCategoryRequest;
import com.example.demo.entities.Carts;
import com.example.demo.entities.Categories;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/carts")
    public List<Carts> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/carts/{cartId}")
    public ResponseEntity<Carts> getCartById(@PathVariable Integer cartId) {
        Optional<Carts> carts = cartService.getCartById(cartId);
        return carts.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestBody AddToBasketRequest addToBasketRequest) {
        try {
            cartService.callAddToCartProcedure(addToBasketRequest.getUserId(),addToBasketRequest.getProductId(),addToBasketRequest.getQuantity());
            return new ResponseEntity<>("Add to basket successfully ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed Add to basket: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/removeFromCart")
    public ResponseEntity<String> removeFromCart(@RequestBody RemoveToBasketRequest removeToBasketRequest) {
        try {
            cartService.callRemoveToCartProcedure( removeToBasketRequest.getUserId(),removeToBasketRequest.getProductId(),removeToBasketRequest.getQuantity());
            return new ResponseEntity<>("Remove From basket successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed remove from basket: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @PostMapping("/clearCart/{id}")
    public ResponseEntity<String> clearCart(@PathVariable Integer id) {
        try {
            cartService.callClearCartProcedure(id);
            return new ResponseEntity<>("Cart clear successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to clear cart: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     */
}
