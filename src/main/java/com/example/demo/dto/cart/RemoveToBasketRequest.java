package com.example.demo.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemoveToBasketRequest {
    private Integer userId;
    private Integer productId;
}


