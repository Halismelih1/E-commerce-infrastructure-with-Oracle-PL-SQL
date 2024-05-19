package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "ORDERITEMS")
public class OrderItems {
    @Id
    @Column(name = "ORDERITEMID")
    private Integer orderItemId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRODUCTID")
    private Integer productId;

    @Column(name = "ORDERID")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "ORDERID", insertable = false, updatable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "PRODUCTID", insertable = false, updatable = false)
    private Products product;
}
