package com.washi.subnpayservice.service;

import com.washi.subnpayservice.entity.UserPaymentMethod;

import java.util.List;
import java.util.Optional;

public interface UserPaymentMethodService  extends CrudService<UserPaymentMethod, Long>{
    List<Optional<UserPaymentMethod>> findByUserId(String userId) throws Exception;
    List<Optional<UserPaymentMethod>> findByPaymentMethodId(String paymentMethodId) throws Exception;
    public UserPaymentMethod createUserPaymentMethod(UserPaymentMethod userPaymentMethod);
    public UserPaymentMethod updateUserPaymentMethod(UserPaymentMethod userPaymentMethod);
    public UserPaymentMethod deleteUserPaymentMethod(UserPaymentMethod userPaymentMethod);
    //Optional<UserPaymentMethod> findByIdOptional(String id) throws Exception;
}
