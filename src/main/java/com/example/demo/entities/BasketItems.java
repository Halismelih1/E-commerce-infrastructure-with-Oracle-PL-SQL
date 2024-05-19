package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "CARTITEMS")
public class BasketItems {
    @Id
    @Column(name = "CARTITEMID")
    private Integer cartItemId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRODUCTID")
    private Integer productId;

    @Column(name = "CARTID")
    private Integer cartId;

    @ManyToOne
    @JoinColumn(name = "CARTID", insertable = false, updatable = false)
    private Carts cart;

    @ManyToOne
    @JoinColumn(name = "PRODUCTID", insertable = false, updatable = false)
    private Products product;
}

