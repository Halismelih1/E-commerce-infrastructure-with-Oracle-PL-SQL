package com.example.demo.dto.favourites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostFavouriteProducts {
    private Integer productId;
    private String productName;
    private Integer productPrice;
    private int favCount;
}
