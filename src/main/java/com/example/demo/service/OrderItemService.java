package com.example.demo.service;

import com.example.demo.entities.OrderItems;
import com.example.demo.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    private JdbcTemplate jdbcTemplate;

    public OrderItemService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrderItems> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
}