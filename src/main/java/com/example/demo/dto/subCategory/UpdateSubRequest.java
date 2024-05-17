package com.example.demo.dto.subCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateSubRequest {
    private Integer subCategoryId;
    private String subCategoryName;
    private String subCategoryDesc;
    private Integer categoryId;
}
