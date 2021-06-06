package com.washi.geographicsservice.repository;

import com.washi.geographicsservice.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Long> {
    public Country findById(String id);
    public List<Country> findByName(String name);
}
