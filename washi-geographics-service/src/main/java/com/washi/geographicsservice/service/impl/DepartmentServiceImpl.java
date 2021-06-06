package com.washi.geographicsservice.service.impl;

import com.washi.geographicsservice.entity.Country;
import com.washi.geographicsservice.entity.Department;
import com.washi.geographicsservice.repository.DepartmentRepository;
import com.washi.geographicsservice.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> findDepartmentsByCountry(Country country) {
        return departmentRepository.findByCountry(country);
    }

    @Override
    public Department getDepartment(Long id){
        return departmentRepository.findById(id).orElse(null);
    }
}
