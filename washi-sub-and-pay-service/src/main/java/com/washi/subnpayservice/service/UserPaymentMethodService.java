package com.washi.subnpayservice.service;

import com.washi.subnpayservice.entity.UserPaymentMethod;

import java.util.List;
import java.util.Optional;

public interface UserPaymentMethodService{
    public List<UserPaymentMethod> findAll();

    public UserPaymentMethod createUserPaymentMethod(UserPaymentMethod userPaymentMethod);
    public UserPaymentMethod updateUserPaymentMethod(UserPaymentMethod userPaymentMethod);
    public UserPaymentMethod deleteUserPaymentMethod(UserPaymentMethod userPaymentMethod);
    public UserPaymentMethod getUserPaymentMethod(Long id);
}
