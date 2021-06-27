package com.washi.subnpayservice.service;

import com.washi.subnpayservice.entity.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    public List<Subscription> findAll();

    public Subscription createSubscription(Subscription subscription);
    public Subscription updateSubscription(Subscription subscription);
    public Subscription deleteSubscription(Subscription subscription);

    public Subscription getSubscription(Long id);}
