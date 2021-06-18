package com.washi.businessservice.entity;

import com.washi.businessservice.model.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "laundry_service_saterial")
public class LaundryServiceMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private User user;

    @Column(name = "laundry_id")
    private Long laundryId;


    @Transient
    private ServiceMaterial serviceMaterial;

    @Column(name = "service_material_id")
    private Long serviceMaterialId;

    private Float price;

    private String descriptions;

    @Column(name = "estimate_time_in_days")
    private Integer estimateTimeInDays;

    @Column(name = "discount_percentage")
    private Float discountPercentage;

    private Float raiting;

}
