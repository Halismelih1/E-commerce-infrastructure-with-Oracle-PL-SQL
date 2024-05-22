package com.example.demo.dto.favourites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFavouriteList {
    private Integer productId;
    private String productName;
    private Integer productPrice;

}



