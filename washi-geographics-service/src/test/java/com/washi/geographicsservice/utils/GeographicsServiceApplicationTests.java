package com.washi.geographicsservice.utils;

import com.washi.geographicsservice.entity.Country;
import com.washi.geographicsservice.entity.Department;
import com.washi.geographicsservice.entity.District;
import com.washi.geographicsservice.service.CountryService;
import com.washi.geographicsservice.service.DistrictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootTest
class GeographicsServiceApplicationTests {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    void countries() {
        List<Country> countries = webClientBuilder.build()
                .get()
                .uri("http://localhost:8090/geographics/countries")
                .retrieve()
                .bodyToFlux(Country.class)
                .collectList()
                .block();
        System.out.println(countries);
    }

    @Test
    void countryById() {
        long idL = 1;
        String id = String.valueOf(idL);
        Country country = webClientBuilder.build()
                .get()
                .uri("http://localhost:8090/geographics/countries/"+id)
                .retrieve()
                .bodyToMono(Country.class)
                .block();
        System.out.println(country);
    }

    @Test
    void departmentsByCountry() {
        long idL = 1L;
        String id = String.valueOf(idL);
        List<Department> departments = webClientBuilder.build()
                .get()
                .uri("http://localhost:8090/geographics/departments?countryId="+id)
                .retrieve()
                .bodyToFlux(Department.class)
                .collectList()
                .block();
        System.out.println(departments);
    }

    @Test
    void departmentById() {
        long idL = 1L;
        String id = String.valueOf(idL);
        Department department = webClientBuilder.build()
                .get()
                .uri("http://localhost:8090/geographics/districts/" + id)
                .retrieve()
                .bodyToMono(Department.class)
                .block();
        System.out.println(department);

    }

    @Test
    void districtsByDepartment() {
        long idL = 1L;
        String id = String.valueOf(idL);
        List<District> districts = webClientBuilder.build()
                .get()
                .uri("http://localhost:8090/geographics/districts?DepartmentId="+id)
                .retrieve()
                .bodyToFlux(District.class)
                .collectList()
                .block();
        System.out.println(districts);
    }

    @Test
    void districtById() {
        long idL = 1L;
        String id = String.valueOf(idL);
        District district = webClientBuilder.build()
                .get()
                .uri("http://localhost:8090/geographics/districts/" + id)
                .retrieve()
                .bodyToMono(District.class)
                .block();
        System.out.println(district);

    }
}
