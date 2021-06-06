package com.washi.geographicsservice.controller;

import com.washi.geographicsservice.entity.Department;
import com.washi.geographicsservice.entity.District;
import com.washi.geographicsservice.service.DistrictService;
import com.washi.geographicsservice.service.DistrictService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/geographics/districts")
@Api(tags = "Geographics")
public class DistrictController{

    @Autowired
    DistrictService districtService;

    @GetMapping
    public ResponseEntity<List<District>> listAllDistricts(@RequestParam(name = "DepartmentId" , required = false) Long DepartmentId ) {
        List<District> districts =  new ArrayList<>();
        if (null ==  DepartmentId) {
            districts = districtService.findAll();
            if (districts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }else{
            Department Department= new Department();
            Department.setId(DepartmentId);
            districts = districtService.findDistrictsByDepartment(Department);
            if ( null == districts ) {
                log.error("Districts with Department id {} not found.", DepartmentId);
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(districts);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<District> getDistrict(@PathVariable("id") Long id) {
        District district =  districtService.getDistrict(id);
        if (null==district){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(district);
    }
}
