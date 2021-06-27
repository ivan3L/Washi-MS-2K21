package com.washi.subnpayservice.repository;

import com.washi.subnpayservice.entity.Subscription;
import com.washi.subnpayservice.entity.UserPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserPaymentMethodsRepository extends JpaRepository<UserPaymentMethod, Long> {
    public UserPaymentMethod findById(String id);
    public UserPaymentMethod findByNumberUserPaymentMethod(String numberUserPaymentMethod);
    List<Optional<UserPaymentMethod>> findByUserId(String userId);
    List<Optional<UserPaymentMethod>> findByPaymentMethodId(String paymentMethodId);
    //Optional<UserPaymentMethod> findByIdOptional(String id) throws Exception;
}
