package com.example.demo.service;

import com.example.demo.entities.BasketItems;
import com.example.demo.repository.BasketItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BasketItemService {

    @Autowired
    private BasketItemRepository basketItemRepository;
    private JdbcTemplate jdbcTemplate;

    public BasketItemService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BasketItems> getAllBasketItems() {
        return basketItemRepository.findAll();
    }

}