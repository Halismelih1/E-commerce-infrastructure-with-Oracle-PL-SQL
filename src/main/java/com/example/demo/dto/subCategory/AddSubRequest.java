package com.example.demo.dto.subCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddSubRequest {
    private String subCategoryName;
    private Integer categoryId;

}


