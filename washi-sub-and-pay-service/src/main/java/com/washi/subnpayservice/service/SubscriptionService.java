package com.washi.subnpayservice.service;

import com.washi.subnpayservice.entity.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService  extends CrudService<Subscription, Long>{
    List<Optional<Subscription>> findByUserId(String userId) throws Exception;
    List<Optional<Subscription>> findByPlanId(String planId) throws Exception;
    public Subscription createSubscription(Subscription subscription);
    public Subscription updateSubscription(Subscription subscription);
    public Subscription deleteSubscription(Subscription subscription);
    //Optional<Subscription> findByIdOptional(String id) throws Exception;
}
