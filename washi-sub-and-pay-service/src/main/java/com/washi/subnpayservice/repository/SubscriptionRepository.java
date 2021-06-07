package com.washi.subnpayservice.repository;

import com.washi.subnpayservice.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    public Subscription findById(String id);
    List<Optional<Subscription>> findByUserId(String userId);
    List<Optional<Subscription>> findByPlanId(String planId);
    //Optional<Subscription> findByIdOptional(String id) throws Exception;
}
