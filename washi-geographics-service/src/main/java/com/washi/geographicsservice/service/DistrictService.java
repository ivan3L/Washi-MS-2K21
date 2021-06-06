package com.washi.geographicsservice.service;

import com.washi.geographicsservice.entity.Department;
import com.washi.geographicsservice.entity.District;

import java.util.List;

public interface DistrictService{
    public List<District> findAll();
    public List<District> findDistrictsByDepartment(Department department);

    public District getDistrict(Long id);
}
