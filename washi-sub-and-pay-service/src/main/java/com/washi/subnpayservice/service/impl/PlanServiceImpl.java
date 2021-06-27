package com.washi.subnpayservice.service.impl;

import com.washi.subnpayservice.entity.Plan;
import com.washi.subnpayservice.repository.PlanRepository;
import com.washi.subnpayservice.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository PlanRepository;

    @Override
    public Plan getById(Long id) {
        Plan Plan = PlanRepository.findById(id).orElse(null);
        return Plan;
    }
}
