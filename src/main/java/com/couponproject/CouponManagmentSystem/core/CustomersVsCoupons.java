package com.couponproject.CouponManagmentSystem.core;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "CUSTOMERS_VS_COUPONS")
//=== lombok
@ToString
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CustomerVsCouponId.class)
public class CustomersVsCoupons {
    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
}
