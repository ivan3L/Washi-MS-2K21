package com.washi.geographicsservice.controller;

import com.washi.geographicsservice.entity.Country;
import com.washi.geographicsservice.entity.Department;
import com.washi.geographicsservice.service.DepartmentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/geographics/departments")
@Api(tags = "Geographics")
public class DepartmentController{

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> listAllDepartments(@RequestParam(name = "countryId" , required = false) Long countryId ) {
        List<Department> departments =  new ArrayList<>();
        if (null ==  countryId) {
            departments = departmentService.findAll();
            if (departments.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }else{
            Country Country= new Country();
            Country.setId(countryId);
            departments = departmentService.findDepartmentsByCountry(Country);
            if ( null == departments ) {
                log.error("Departments with Country id {} not found.", countryId);
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(departments);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") Long id) {
        Department department =  departmentService.getDepartment(id);
        if (null==department){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }
}
