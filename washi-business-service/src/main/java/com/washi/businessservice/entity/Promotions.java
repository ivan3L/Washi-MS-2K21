package com.washi.businessservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "promotions")
public class Promotions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private LaundryServiceMaterial laundryServiceMaterial;

    @Column(name = "laundry_id")
    private Long laundryServiceMaterialId;

    private Float discountPorcentage;

    private Date initialDate;

    private Date endingDate;
}
