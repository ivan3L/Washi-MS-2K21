package com.washi.subnpayservice.service.impl;

import com.washi.subnpayservice.client.UserClient;
import com.washi.subnpayservice.entity.PaymentMethod;
import com.washi.subnpayservice.entity.UserPaymentMethod;
import com.washi.subnpayservice.entity.UserPaymentMethod;
import com.washi.subnpayservice.model.User;
import com.washi.subnpayservice.repository.PaymentMethodRepository;
import com.washi.subnpayservice.repository.PaymentMethodRepository;
import com.washi.subnpayservice.repository.UserPaymentMethodsRepository;
import com.washi.subnpayservice.service.UserPaymentMethodService;
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
    public List<UserPaymentMethod> findAll() {
        return userPaymentMethodRepository.findAll();
    }

    @Override
    public UserPaymentMethod createUserPaymentMethod(UserPaymentMethod userPaymentMethod) {
        UserPaymentMethod userPaymentMethodDB = userPaymentMethodRepository.findByNumberUserPaymentMethod(userPaymentMethod.getNumberUserPaymentMethod());
        if(userPaymentMethodDB != null){
            return userPaymentMethodDB;
        }
        userPaymentMethod.setState("CREATED");
        userPaymentMethodDB = userPaymentMethodRepository.save(userPaymentMethod);

        return userPaymentMethodDB;
    }

    @Override
    public UserPaymentMethod updateUserPaymentMethod(UserPaymentMethod userPaymentMethod) {
        UserPaymentMethod userPaymentMethodDB = getUserPaymentMethod(userPaymentMethod.getId());
        if(userPaymentMethodDB == null){
            return null;
        }
        userPaymentMethodDB.setUserId(userPaymentMethod.getUserId());
        userPaymentMethodDB.setNumberUserPaymentMethod(userPaymentMethod.getNumberUserPaymentMethod());
        userPaymentMethodDB.setPaymentMethodId(userPaymentMethod.getPaymentMethodId());

        return userPaymentMethodRepository.save(userPaymentMethodDB);
    }

    @Override
    public UserPaymentMethod deleteUserPaymentMethod(UserPaymentMethod userPaymentMethod) {
        UserPaymentMethod userPaymentMethodDB = getUserPaymentMethod(userPaymentMethod.getId());
        if(userPaymentMethodDB == null){
            return null;
        }
        userPaymentMethodDB.setState("DELETED");
        return userPaymentMethodRepository.save(userPaymentMethodDB);
    }

    @Override
    public UserPaymentMethod getUserPaymentMethod(Long id) {
        UserPaymentMethod userPaymentMethod = userPaymentMethodRepository.findById(id).orElse(null);
        if (userPaymentMethod != null){
            User user = userClient.getUser(userPaymentMethod.getUserId()).getBody();
            userPaymentMethod.setUser(user);
        }
        return userPaymentMethod;
    }
}
