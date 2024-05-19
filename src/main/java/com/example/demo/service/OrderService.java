package com.example.demo.service;

import com.example.demo.entities.Carts;
import com.example.demo.entities.Orders;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private JdbcTemplate jdbcTemplate;

    public OrderService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    //Oracle PL/SQL prosedürleri çağır
    public void callCreateOrderProcedure(Integer userId) {
        String sql = "{call ORDERMANAGER.CreateOrder(?)}";
        jdbcTemplate.update(sql, userId);

    }


}
