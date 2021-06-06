package com.washi.geographicsservice.service.impl;

import com.washi.geographicsservice.entity.Country;
import com.washi.geographicsservice.repository.CountryRepository;
import com.washi.geographicsservice.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountry(Long id) {
        return countryRepository.findById(id).orElse(null);
    }
}
