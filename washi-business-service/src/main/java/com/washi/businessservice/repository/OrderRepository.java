package com.washi.businessservice.repository;

import com.washi.businessservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public Order findById(String id);
}
