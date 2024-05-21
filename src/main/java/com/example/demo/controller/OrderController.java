package com.example.demo.controller;

import com.example.demo.dto.order.OrderItem;
import com.example.demo.dto.order.CreateOrderRequest;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrderController {
    @Autowired
    OrderService orderService;


    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        try {
            orderService.callCreateOrderProcedure(createOrderRequest.getUserId());
            return new ResponseEntity<>("Order created successfully ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/viewOrders/{userId}")
    public List<OrderItem> viewOrders(@PathVariable int userId) {
        return orderService.callViewOrdersProcedure(userId);
    }



}


