package com.example.demo.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private int orderId;
    private Date orderDate;
    private String orderDetails;
    private int totalOrderPrice;

}
