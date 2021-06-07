package com.washi.subnpayservice.entity;
import com.washi.subnpayservice.model.User;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "user_payment_methods")
public class UserPaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @Transient
    private PaymentMethod paymentMethod;

    @Column(name = "payment_method_id")
    private Long paymentMethodId;
}
