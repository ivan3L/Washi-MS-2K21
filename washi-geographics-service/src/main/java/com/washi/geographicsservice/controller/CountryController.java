package com.washi.geographicsservice.controller;

import com.washi.geographicsservice.entity.Country;
import com.washi.geographicsservice.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/geographics/countries")
@Api(tags = "Geographics")
public class CountryController {

    @Autowired
    private CountryService countryService;
    @GetMapping
    @ApiOperation(value = "Retorna una lista de paises")
    public ResponseEntity<List<Country>> listCountries(){
        List<Country> countries = new ArrayList<>();
        countries = countryService.findAll();
        if(countries.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(countries);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorna un pais segun su id")
    public ResponseEntity<Country> getCountry(@PathVariable("id") Long id) {
        Country country =  countryService.getCountry(id);
        if (null==country){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(country);
    }

}
