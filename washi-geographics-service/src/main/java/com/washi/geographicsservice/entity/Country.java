package com.washi.geographicsservice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}