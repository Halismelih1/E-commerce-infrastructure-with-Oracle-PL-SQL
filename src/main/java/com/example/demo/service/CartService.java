package com.example.demo.service;


import com.example.demo.entities.Carts;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    private JdbcTemplate jdbcTemplate;

    public CartService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Carts> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Carts> getCartById(Integer cartId) {
        return cartRepository.findById(cartId);
    }

    //Oracle PL/SQL prosedürleri çağır
    public void callAddToCartProcedure(Integer userId, Integer productId, Integer quantity) {
        String sql = "{call BASKETMANAGER.AddProductToCart(?, ?, ?)}";
        jdbcTemplate.update(sql, userId, productId, quantity);

    }

    public void callRemoveToCartProcedure(Integer userId, Integer productId, Integer quantity) {
        String sql = "{call BASKETMANAGER.RemoveProductFromCart(?, ?, ?)}";
        jdbcTemplate.update(sql, userId, productId, quantity);

    }

    /*
    public void callClearCartProcedure(Integer id) {
        String sql = "{call BASKETMANAGER.ClearCart(?)}";
        jdbcTemplate.update(sql, id);

    } */


     /*
    public void callViewCartProcedure(int id) {
        String sql = "{call BASKETMANAGER.ViewCart(?)}";
        jdbcTemplate.update(sql, id);

    } */
}
