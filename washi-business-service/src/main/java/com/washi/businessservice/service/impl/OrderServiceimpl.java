package com.washi.businessservice.service.impl;

import com.washi.businessservice.client.UserClient;
import com.washi.businessservice.entity.Order;
import com.washi.businessservice.exception.ResourceNotFoundException;
import com.washi.businessservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceimpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserClient userClient;

    @Override
    public List<Order> findAll() {return orderRepository.findAll();}

    @Override
    public Order createOrder(Order order){
        //falta el patron
        return orderRepository.save(order);
    }
    @Override
    public Order updateOrder(Order order){
        Order orderDB = getOrder(order.getId());
        if(orderDB == null){
            return null;
        }
        orderDB.setDate(order.getDate());
        orderDB.setDeliveryAddress(order.getDeliveryAddress());
        orderDB.setDeliveryDate(order.getDeliveryDate());
        orderDB.setOrderAmount(order.getOrderAmount());
        orderDB.setOrderStatus(order.getOrderStatus());

        return orderRepository.save(orderDB);
    }
    @Override
    public Order deleteOrder(Order order){
        Order orderDB = getOrder(order.getId());
        return  orderRepository.save(order);
    }
    @Override
    public Order getOrder(Long id){
        //falta el patron
        Order order = orderRepository.findById(id).orElse(null);
        return order;
    }




}
