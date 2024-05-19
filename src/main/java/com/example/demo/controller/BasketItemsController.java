package com.example.demo.controller;

import com.example.demo.entities.BasketItems;
import com.example.demo.service.BasketItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class BasketItemsController {
    @Autowired
    private BasketItemService basketItemService;

    @GetMapping("/basketItems")
    public List<BasketItems> getAllBasketItems() {
        return basketItemService.getAllBasketItems();
    }

    }