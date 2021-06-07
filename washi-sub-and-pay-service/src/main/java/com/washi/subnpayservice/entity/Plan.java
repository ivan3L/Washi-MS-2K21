package com.washi.subnpayservice.entity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float price;

    @Column(name = "duration_in_days")
    private Integer durationInDays;
}
