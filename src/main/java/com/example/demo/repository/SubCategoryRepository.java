package com.example.demo.repository;

import com.example.demo.entities.SubCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategories,Integer> {
}
