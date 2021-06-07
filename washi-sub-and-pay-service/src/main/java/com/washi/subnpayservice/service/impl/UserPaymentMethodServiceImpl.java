package com.washi.subnpayservice.service.impl;

import com.washi.subnpayservice.client.UserClient;
import com.washi.subnpayservice.entity.PaymentMethod;
import com.washi.subnpayservice.entity.UserPaymentMethod;
import com.washi.subnpayservice.model.User;
import com.washi.subnpayservice.repository.PaymentMethodRepository;
import com.washi.subnpayservice.repository.UserPaymentMethodsRepository;
import com.washi.subnpayservice.service.UserPaymentMethodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserPaymentMethodServiceImpl implements UserPaymentMethodService {
    @Autowired
    UserPaymentMethodsRepository userPaymentMethodRepository;

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    UserClient userClient;

    @Override
    public UserPaymentMethod save(UserPaymentMethod entity) throws Exception {
        return null;
    }

    @Override
    public List<UserPaymentMethod> findAll() {
        return userPaymentMethodRepository.findAll();
    }

    @Override
    public List<Optional<UserPaymentMethod>> findByUserId(String userId) throws Exception {
        return userPaymentMethodRepository.findByUserId(userId);
    }

    @Override
    public List<Optional<UserPaymentMethod>> findByPaymentMethodId(String paymentMethodId) throws Exception {
        return userPaymentMethodRepository.findByPaymentMethodId(paymentMethodId);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserPaymentMethod> findById(Long aLong) throws Exception {
        return userPaymentMethodRepository.findById( aLong);
    }

    @Override
    public UserPaymentMethod update(UserPaymentMethod entity) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws Exception {

    }

    @Override
    public UserPaymentMethod createUserPaymentMethod(UserPaymentMethod userPaymentMethod) {
        UserPaymentMethod userPaymentMethodDB = userPaymentMethodRepository.findById(userPaymentMethod.getId().toString());
        if(userPaymentMethodDB != null){
            return userPaymentMethodDB;
        }
        userPaymentMethodDB = userPaymentMethodRepository.save(userPaymentMethod);

        return userPaymentMethodDB;
    }

    @Override
    public UserPaymentMethod updateUserPaymentMethod(UserPaymentMethod userPaymentMethod) {
        UserPaymentMethod userPaymentMethodDB = getUserPaymentMethod(userPaymentMethod.getId());
        if(userPaymentMethodDB == null){
            return null;
        }
        userPaymentMethodDB.setPaymentMethodId(userPaymentMethod.getPaymentMethodId());
        userPaymentMethodDB.setUserId(userPaymentMethod.getUserId());

        return userPaymentMethodRepository.save(userPaymentMethodDB);
    }

    @Override
    public UserPaymentMethod deleteUserPaymentMethod(UserPaymentMethod userPaymentMethod) {
        UserPaymentMethod userPaymentMethodDB = getUserPaymentMethod(userPaymentMethod.getId());
        if(userPaymentMethodDB == null){
            return null;
        }
        return userPaymentMethodRepository.save(userPaymentMethodDB);
    }
/*
    @Override
    public Optional<UserPaymentMethod> findByIdOptional(String id) throws Exception {
        return userPaymentMethodRepository.findByIdOptional(id);
    }
*/
    public UserPaymentMethod getUserPaymentMethod(Long id) {
        UserPaymentMethod userPaymentMethod = userPaymentMethodRepository.findById(id).orElse(null);
        if (userPaymentMethod != null){
            User user = userClient.getUser(userPaymentMethod.getUserId()).getBody();
            userPaymentMethod.setUser(user);
            PaymentMethod paymentMethod = paymentMethodRepository.getById(userPaymentMethod.getPaymentMethodId().toString());
            userPaymentMethod.setPaymentMethod(paymentMethod);
        }
        return userPaymentMethod;
    }

}
