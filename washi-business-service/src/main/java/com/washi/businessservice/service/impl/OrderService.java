package com.washi.businessservice.service.impl;

import com.washi.businessservice.entity.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    public List<Order> findAll();
    public Order createOrder(Order order);
    public Order updateOrder(Order order);
    public ResponseEntity<Object> deleteOrder(Long orderId);
    public Order getOrder(Long id);

}
