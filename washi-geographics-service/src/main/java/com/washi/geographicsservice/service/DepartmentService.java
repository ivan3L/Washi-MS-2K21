package com.washi.geographicsservice.service;


import com.washi.geographicsservice.entity.Country;
import com.washi.geographicsservice.entity.Department;

import java.util.List;

public interface DepartmentService {

    public List<Department> findAll();
    public List<Department> findDepartmentsByCountry(Country country);

    public Department getDepartment(Long id);
}
