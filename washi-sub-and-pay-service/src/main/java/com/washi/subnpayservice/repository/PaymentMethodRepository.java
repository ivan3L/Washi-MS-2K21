package com.washi.subnpayservice.repository;

import com.washi.subnpayservice.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    public PaymentMethod getById(String id);
}
