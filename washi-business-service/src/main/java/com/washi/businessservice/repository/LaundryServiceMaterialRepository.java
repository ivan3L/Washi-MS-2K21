package com.washi.businessservice.repository;

import com.washi.businessservice.entity.LaundryServiceMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface LaundryServiceMaterialRepository extends JpaRepository<LaundryServiceMaterial, Long> {

    LaundryServiceMaterial findByServiceMaterialId(Long serviceMaterialid);
}
