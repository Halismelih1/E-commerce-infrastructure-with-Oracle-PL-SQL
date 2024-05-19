package com.example.demo.repository;

import com.example.demo.entities.BasketItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItems,Integer> {
}
