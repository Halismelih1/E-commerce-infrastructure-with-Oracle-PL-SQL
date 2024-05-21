package com.example.demo.dto.favourites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddFav {
    private Integer userId;
    private Integer productId;
}
