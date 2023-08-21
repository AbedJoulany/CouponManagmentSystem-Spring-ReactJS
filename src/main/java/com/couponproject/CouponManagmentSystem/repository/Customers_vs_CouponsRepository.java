package com.couponproject.CouponManagmentSystem.repository;

import com.couponproject.CouponManagmentSystem.core.Coupon;
import com.couponproject.CouponManagmentSystem.core.CustomerVsCouponId;
import com.couponproject.CouponManagmentSystem.core.CustomersVsCoupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface Customers_vs_CouponsRepository extends JpaRepository<CustomersVsCoupons, CustomerVsCouponId> {

    @Query(value="select * from customers_vs_coupons c where c.COUPON_ID = ?1" , nativeQuery = true)
    Set<Coupon> findPurchaseByCouponID(Long COUPON_ID);

    @Query(value="select * from customers_vs_coupons c  where c.CUSTOMER_ID = ?1" , nativeQuery = true)
    Set<Coupon> findPurchaseByCoustomerID(Long CUSTOMER_ID);

}
