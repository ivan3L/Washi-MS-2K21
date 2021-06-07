package com.washi.subnpayservice.repository;

import com.washi.subnpayservice.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    public Plan getById(String id);
}
