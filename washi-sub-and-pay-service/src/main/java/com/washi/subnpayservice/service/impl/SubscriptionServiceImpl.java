package com.washi.subnpayservice.service.impl;

import com.washi.subnpayservice.client.UserClient;
import com.washi.subnpayservice.entity.Plan;
import com.washi.subnpayservice.entity.Subscription;
import com.washi.subnpayservice.model.User;
import com.washi.subnpayservice.repository.PlanRepository;
import com.washi.subnpayservice.repository.SubscriptionRepository;
import com.washi.subnpayservice.service.PlanService;
import com.washi.subnpayservice.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    UserClient userClient;

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription createSubscription(Subscription subscription) {
        Subscription subscriptionDB = subscriptionRepository.findByNumberSubscription(subscription.getNumberSubscription());
        if(subscriptionDB != null){
            return subscriptionDB;
        }
        subscription.setState("CREATED");
        subscriptionDB = subscriptionRepository.save(subscription);

        return subscriptionDB;
    }

    @Override
    public Subscription updateSubscription(Subscription subscription) {
        Subscription subscriptionDB = getSubscription(subscription.getId());
        if(subscriptionDB == null){
            return null;
        }
        subscriptionDB.setUserId(subscription.getUserId());
        subscriptionDB.setNumberSubscription(subscription.getNumberSubscription());
        subscriptionDB.setPlanId(subscription.getPlanId());

        return subscriptionRepository.save(subscriptionDB);
    }

    @Override
    public Subscription deleteSubscription(Subscription subscription) {
        Subscription subscriptionDB = getSubscription(subscription.getId());
        if(subscriptionDB == null){
            return null;
        }
        subscriptionDB.setState("DELETED");
        return subscriptionRepository.save(subscriptionDB);
    }

    @Override
    public Subscription getSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).orElse(null);
        if (subscription != null){
            User user = userClient.getUser(subscription.getUserId()).getBody();
            subscription.setUser(user);
        }
        return subscription;
    }
}
