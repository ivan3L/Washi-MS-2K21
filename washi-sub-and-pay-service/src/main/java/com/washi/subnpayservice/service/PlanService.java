package com.washi.subnpayservice.service;

import com.washi.subnpayservice.entity.Plan;

public interface PlanService extends CrudService<Plan,Long>{
    public Plan getById(Long id);
}
