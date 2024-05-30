package com.example.demo.service;


import com.example.demo.dto.cart.ProductItem;
import com.example.demo.entities.Carts;
import com.example.demo.repository.CartRepository;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
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

    public void callRemoveToCartProcedure(Integer userId, Integer productId) {
        String sql = "{call BASKETMANAGER.RemoveProductFromCart(?, ?)}";
        jdbcTemplate.update(sql, userId, productId);

    }


    public void callClearCartProcedure(Integer userId) {
        String sql = "{call BASKETMANAGER.ClearCart(?)}";
        jdbcTemplate.update(sql, userId);

    }

    public List<ProductItem> callViewCartProcedure(Integer userId) {
        String sql = "{call BASKETMANAGER.ViewCart(?, ?)}";

        List<ProductItem> productItems = jdbcTemplate.execute(
                (Connection conn) -> {
                    CallableStatement cs = conn.prepareCall(sql);
                    cs.setInt(1, userId);
                    cs.registerOutParameter(2, OracleTypes.CURSOR);
                    return cs;
                },
                (CallableStatement cs) -> {
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(2);

                    List<ProductItem> items = new ArrayList<>();

                    while (rs.next()) {
                        ProductItem item = new ProductItem(
                                rs.getInt("productId"),
                                rs.getString("productName"),
                                rs.getInt("productPrice"),
                                rs.getInt("quantity"),
                                rs.getInt("totalItemPrice"),
                                rs.getInt("totalCartAmount")
                        );
                        items.add(item);
                    }

                    return items;
                }
        );

        return productItems;
    }
}




