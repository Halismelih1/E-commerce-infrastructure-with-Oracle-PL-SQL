package com.example.demo.controller;

import com.example.demo.dto.cart.AddToBasketRequest;
import com.example.demo.dto.cart.RemoveToBasketRequest;
import com.example.demo.dto.order.CreateOrderRequest;
import com.example.demo.dto.order.ViewOrdersRequest;
import com.example.demo.entities.Carts;
import com.example.demo.entities.Orders;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Orders> getCartById(@PathVariable int id) {
        Optional<Orders> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        try {
            orderService.callCreateOrderProcedure(createOrderRequest.getUserId());
            return new ResponseEntity<>("Order created successfully ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
