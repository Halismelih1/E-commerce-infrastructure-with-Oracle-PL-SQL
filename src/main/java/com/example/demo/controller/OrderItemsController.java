package com.example.demo.controller;

import com.example.demo.entities.BasketItems;
import com.example.demo.entities.OrderItems;
import com.example.demo.service.BasketItemService;
import com.example.demo.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderItemsController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/orderItems")
    public List<OrderItems> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

}