package com.washi.geographicsservice.service.impl;

import com.washi.geographicsservice.entity.Country;
import com.washi.geographicsservice.entity.Department;
import com.washi.geographicsservice.entity.District;
import com.washi.geographicsservice.repository.DistrictRepository;
import com.washi.geographicsservice.service.DistrictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DistrictServiceImpl implements DistrictService{

    @Autowired
    DistrictRepository districtRepository;

    @Override
    public List<District> findAll() {
        return districtRepository.findAll();
    }

    @Override
    public List<District> findDistrictsByDepartment(Department department) {
        return districtRepository.findByDepartment(department);
    }

    @Override
    public District getDistrict(Long id){
        return districtRepository.findById(id).orElse(null);
    }}
