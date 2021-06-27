package com.washi.subnpayservice.service.impl;

import com.washi.subnpayservice.entity.PaymentMethod;
import com.washi.subnpayservice.repository.PaymentMethodRepository;
import com.washi.subnpayservice.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod getById(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElse(null);
        return paymentMethod;
    }
}
