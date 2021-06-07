package com.washi.subnpayservice.service;

import com.washi.subnpayservice.entity.PaymentMethod;

public interface PaymentMethodService extends CrudService<PaymentMethod,Long>{
    public PaymentMethod getById(Long id);
}
