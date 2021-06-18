package com.washi.businessservice.service.impl;

import com.washi.businessservice.entity.LaundryServiceMaterial;

import java.util.List;

public interface LaundryServiceMaterialService {
    public List<LaundryServiceMaterial> findAll();
    public LaundryServiceMaterial createLaundryServiceMaterial(LaundryServiceMaterial laundryServiceMaterial);
    public LaundryServiceMaterial updateLaundryServiceMaterial(LaundryServiceMaterial laundryServiceMaterial);
    public LaundryServiceMaterial deleteLaundryServiceMaterial(LaundryServiceMaterial laundryServiceMaterial);

    public LaundryServiceMaterial getLaundryServiceMaterial(Long id);
}
