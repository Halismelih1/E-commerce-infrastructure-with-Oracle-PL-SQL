package com.example.demo.repository;

import com.example.demo.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItems,Integer> {
}
