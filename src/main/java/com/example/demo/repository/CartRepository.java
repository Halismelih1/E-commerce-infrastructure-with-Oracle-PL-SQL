package com.example.demo.repository;

import com.example.demo.entities.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Carts,Integer> {
}
