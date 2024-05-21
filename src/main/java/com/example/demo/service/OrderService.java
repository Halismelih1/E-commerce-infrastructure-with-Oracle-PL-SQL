package com.example.demo.service;

import com.example.demo.dto.order.OrderItem;
import com.example.demo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public OrderService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //Oracle PL/SQL prosedürleri çağır
    public void callCreateOrderProcedure(Integer userId) {
        String sql = "{call ORDERMANAGER.CreateOrder(?)}";
        jdbcTemplate.update(sql, userId);

    }

    public List<OrderItem> callViewOrdersProcedure(Integer userId) {
        String sql = "{call ORDERMANAGER.ViewOrders(?, ?)}";

        // Call stored procedure and get the results
        List<OrderItem> orderItems = jdbcTemplate.execute(
                (Connection conn) -> {
                    CallableStatement cs = conn.prepareCall(sql);
                    cs.setInt(1, userId);
                    cs.registerOutParameter(2, OracleTypes.CURSOR);
                    return cs;
                },
                (CallableStatement cs) -> {
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(2);

                    List<OrderItem> items = new ArrayList<>();
                    while (rs.next()) {
                        OrderItem item = new OrderItem(
                                rs.getInt("orderId"),
                                rs.getDate("orderDate"),
                                rs.getString("orderDetails"),
                                rs.getInt("totalOrderPrice")
                        );
                        items.add(item);
                    }
                    return items;
                }
        );

        return orderItems;
    }



}

