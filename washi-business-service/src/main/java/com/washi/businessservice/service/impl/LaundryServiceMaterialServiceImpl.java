package com.washi.businessservice.service.impl;


import com.washi.businessservice.client.UserClient;
import com.washi.businessservice.entity.LaundryServiceMaterial;
import com.washi.businessservice.model.User;
import com.washi.businessservice.repository.LaundryServiceMaterialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LaundryServiceMaterialServiceImpl implements LaundryServiceMaterialService {

    @Autowired
    LaundryServiceMaterialRepository laundryServiceMaterialRepository;

    @Autowired
    UserClient userClient;

    @Override
    public List<LaundryServiceMaterial> findAll() {
        return laundryServiceMaterialRepository.findAll();
    }

    @Override
    public LaundryServiceMaterial createLaundryServiceMaterial(LaundryServiceMaterial laundryServiceMaterial) {
        LaundryServiceMaterial laundryServiceMaterialDB = laundryServiceMaterialRepository.findByServiceMaterialId(laundryServiceMaterial.getServiceMaterialId());
        if(laundryServiceMaterialDB != null){
            return laundryServiceMaterialDB;
        }
        laundryServiceMaterialDB = laundryServiceMaterialRepository.save(laundryServiceMaterial);

        return laundryServiceMaterialDB;
    }

    @Override
    public LaundryServiceMaterial updateLaundryServiceMaterial(LaundryServiceMaterial laundryServiceMaterial) {
        LaundryServiceMaterial laundryServiceMaterialDB = getLaundryServiceMaterial(laundryServiceMaterial.getId());
        if(laundryServiceMaterialDB == null){
            return null;
        }
        laundryServiceMaterialDB.setDescriptions(laundryServiceMaterial.getDescriptions());
        laundryServiceMaterialDB.setDiscountPercentage(laundryServiceMaterial.getDiscountPercentage());
        laundryServiceMaterialDB.setEstimateTimeInDays(laundryServiceMaterial.getEstimateTimeInDays());
        laundryServiceMaterialDB.setPrice(laundryServiceMaterial.getPrice());
        laundryServiceMaterialDB.setRaiting(laundryServiceMaterial.getRaiting());
        laundryServiceMaterialDB.setServiceMaterial(laundryServiceMaterial.getServiceMaterial());

        return laundryServiceMaterialRepository.save(laundryServiceMaterialDB);
    }

    @Override
    public LaundryServiceMaterial deleteLaundryServiceMaterial(LaundryServiceMaterial laundryServiceMaterial) {
        LaundryServiceMaterial laundryServiceMaterialDB = getLaundryServiceMaterial(laundryServiceMaterial.getId());
        if(laundryServiceMaterialDB == null){
            return null;
        }
        return laundryServiceMaterialRepository.save(laundryServiceMaterialDB);
    }

    @Override
    public LaundryServiceMaterial getLaundryServiceMaterial(Long id) {
        LaundryServiceMaterial laundryServiceMaterial = laundryServiceMaterialRepository.findById(id).orElse(null);
        if (laundryServiceMaterial != null){
            User user = userClient.getUser(laundryServiceMaterial.getLaundryId()).getBody();
            laundryServiceMaterial.setUser(user);
        }
        return laundryServiceMaterial;
    }
}
