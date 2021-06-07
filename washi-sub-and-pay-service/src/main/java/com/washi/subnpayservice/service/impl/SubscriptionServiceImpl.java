package com.washi.subnpayservice.service.impl;

import com.washi.subnpayservice.client.UserClient;
import com.washi.subnpayservice.entity.Plan;
import com.washi.subnpayservice.entity.Subscription;
import com.washi.subnpayservice.model.User;
import com.washi.subnpayservice.repository.PlanRepository;
import com.washi.subnpayservice.repository.SubscriptionRepository;
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
    public Subscription save(Subscription entity) throws Exception {
        return null;
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public List<Optional<Subscription>> findByUserId(String userId) throws Exception {
        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    public List<Optional<Subscription>> findByPlanId(String planId) throws Exception {
        return subscriptionRepository.findByPlanId(planId);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Subscription> findById(Long aLong) throws Exception {
        return subscriptionRepository.findById( aLong);
    }

    @Override
    public Subscription update(Subscription entity) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
    }

    @Override
    public Subscription createSubscription(Subscription subscription) {
        Subscription subscriptionDB = subscriptionRepository.findById(subscription.getId().toString());
        if(subscriptionDB != null){
            return subscriptionDB;
        }
        subscriptionDB = subscriptionRepository.save(subscription);

        return subscriptionDB;
    }

    @Override
    public Subscription updateSubscription(Subscription subscription) {
        Subscription subscriptionDB = getSubscription(subscription.getId());
        if(subscriptionDB == null){
            return null;
        }
        subscriptionDB.setPlanId(subscription.getPlanId());
        subscriptionDB.setUserId(subscription.getUserId());

        return subscriptionRepository.save(subscriptionDB);
    }

    @Override
    public Subscription deleteSubscription(Subscription subscription) {
        Subscription subscriptionDB = getSubscription(subscription.getId());
        if(subscriptionDB == null){
            return null;
        }
        return subscriptionRepository.save(subscriptionDB);
    }
/*
    @Override
    public Optional<Subscription> findByIdOptional(String id) throws Exception {
        return subscriptionRepository.findByIdOptional(id);
    }
*/
    public Subscription getSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).orElse(null);
        if (subscription != null){
            User user = userClient.getUser(subscription.getUserId()).getBody();
            subscription.setUser(user);
            Plan plan = planRepository.getById(subscription.getPlanId().toString());
            subscription.setPlan(plan);
        }
        return subscription;
    }
}
