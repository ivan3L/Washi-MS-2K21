package com.washi.businessservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ordersdetails")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private LaundryServiceMaterial laundryServiceMaterial;

    @Column(name = "laundry_id")
    private Long laundryServiceMaterialId;

    @Column(name = "order_id")
    private Long OrderId;

    @Transient
    private Order order;

    private Float raiting;



}
