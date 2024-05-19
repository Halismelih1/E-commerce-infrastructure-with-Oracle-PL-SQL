package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Orders")
public class Orders {
    @Id
    @Column(name = "ORDERID")
    private Integer orderId;
    @Column(name = "ORDERDATE")
    private String orderDate;
    @Column(name = "TOTALAMOUNT")
    private String totalAmount;
    @Column(name = "USERID")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "USERID", insertable = false, updatable = false)
    private Users user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderItems> orderItems;
}

