package com.washi.geographicsservice.service;

import com.washi.geographicsservice.entity.Country;

import java.util.List;

public interface CountryService {
    public List<Country> findAll();

    public Country getCountry(Long id);
}
