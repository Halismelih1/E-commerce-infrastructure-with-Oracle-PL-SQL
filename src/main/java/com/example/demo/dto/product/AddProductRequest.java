package com.example.demo.dto.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddProductRequest {

    private String productName;
    private Integer productPrice;
    private String productDesc;
    private Integer categoryId;
    private Integer subCategoryId;


}
