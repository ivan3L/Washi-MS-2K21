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
    public PaymentMethod save(PaymentMethod entity) throws Exception {
        return paymentMethodRepository.save(entity);
    }

    @Override
    public List<PaymentMethod> findAll() throws Exception {
        return paymentMethodRepository.findAll();
    }

    @Override
    public Optional<PaymentMethod> findById(Long aLong) throws Exception {
        return paymentMethodRepository.findById(aLong);
    }

    @Override
    public PaymentMethod update(PaymentMethod entity) throws Exception {
        return paymentMethodRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
        paymentMethodRepository.deleteById(aLong);
    }

    @Override
    public PaymentMethod getById(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElse(null);
        return paymentMethod;
    }
}
