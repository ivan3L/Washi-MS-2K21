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
    public Plan save(Plan entity) throws Exception {
        return PlanRepository.save(entity);
    }

    @Override
    public List<Plan> findAll() throws Exception {
        return PlanRepository.findAll();
    }

    @Override
    public Optional<Plan> findById(Long aLong) throws Exception {
        return PlanRepository.findById(aLong);
    }

    @Override
    public Plan update(Plan entity) throws Exception {
        return PlanRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
        PlanRepository.deleteById(aLong);
    }

    @Override
    public Plan getById(Long id) {
        Plan Plan = PlanRepository.findById(id).orElse(null);
        return Plan;
    }
}
