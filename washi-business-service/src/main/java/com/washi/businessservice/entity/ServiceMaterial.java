package com.washi.businessservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "servicesmaterials")
public class ServiceMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Service service;

    @Column(name = "service_id")
    private Long serviceId;

    @Transient
    private Material material;

    @Column(name = "material_id")
    private Long materialId;
}
