package com.example.demo.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilterProdReq {
    private Integer subCategoryId;
    private Integer minPrice;
    private Integer maxPrice;
    private String productName;
}
