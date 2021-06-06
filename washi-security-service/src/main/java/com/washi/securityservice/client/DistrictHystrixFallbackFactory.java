package com.washi.securityservice.client;

import com.washi.securityservice.model.District;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DistrictHystrixFallbackFactory implements DistrictClient{
    @Override
    public ResponseEntity<District> getDistrict(long id){
        District district = District.builder()
                .name("none")
                .build();
        return ResponseEntity.ok(district);
    }
}
