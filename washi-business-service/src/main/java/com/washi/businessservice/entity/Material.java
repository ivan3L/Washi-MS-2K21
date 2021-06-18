package com.washi.businessservice.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
