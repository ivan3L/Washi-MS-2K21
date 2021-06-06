package com.washi.geographicsservice.repository;

import com.washi.geographicsservice.entity.Country;
import com.washi.geographicsservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    public Department findById(String id);
    public List<Department> findByName(String name);
    public List<Department> findByCountry(Country country);
}
