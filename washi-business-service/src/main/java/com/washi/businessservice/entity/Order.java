package com.washi.businessservice.entity;

import com.washi.businessservice.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private User user;

    @Column(name = "user_id")
    private Long userId;

    private Date date;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "order_amount")
    private Float orderAmount;

    @Column(name = "delivery_date")
    private Long deliveryDate;

    @Transient
    private OrderStatus orderStatus;

    @Column(name = "orderstatus_id")
    private Long orderStatusId;

}
