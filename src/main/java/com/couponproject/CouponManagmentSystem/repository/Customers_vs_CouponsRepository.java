package com.couponproject.CouponManagmentSystem.repository;

import com.couponproject.CouponManagmentSystem.core.Coupon;
import com.couponproject.CouponManagmentSystem.core.CustomerVsCouponId;
import com.couponproject.CouponManagmentSystem.core.CustomersVsCoupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface Customers_vs_CouponsRepository extends JpaRepository<CustomersVsCoupons, CustomerVsCouponId> {

    @Query(value="select * from customers_vs_coupons c where c.COUPON_ID = ?1" , nativeQuery = true)
    List<CustomersVsCoupons> findPurchaseByCouponID(Long COUPON_ID);
    @Query(value="select * from customers_vs_coupons where COUPON_ID = ?1 and CUSTOMER_ID = ?2" , nativeQuery = true)
    Optional<CustomersVsCoupons> findPurchaseByCouponIDAndCustomerID(Long COUPON_ID, Long CUSTOMER_ID);
    @Query(value="select * from customers_vs_coupons c  where c.CUSTOMER_ID = ?1" , nativeQuery = true)
    List<CustomersVsCoupons> findPurchaseByCoustomerID(Long CUSTOMER_ID);

}
