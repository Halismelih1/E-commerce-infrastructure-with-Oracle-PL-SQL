package com.example.demo.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItem {
    private int productId;
    private String productName;
    private int productPrice;
    private int quantity;
    private int totalItemPrice;
}
