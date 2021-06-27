package com.washi.subnpayservice.entity;
import com.washi.subnpayservice.model.User;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_subscription")
    private String numberSubscription;

    @Transient
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @Transient
    private Plan plan;

    @Column(name = "plan_id")
    private Long planId;

    private String state;
}
