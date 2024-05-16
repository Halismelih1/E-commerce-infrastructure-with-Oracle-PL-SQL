package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateCategoryRequest {
    private int id;
    private String categoryName;
    private String categoryDesc;
}
