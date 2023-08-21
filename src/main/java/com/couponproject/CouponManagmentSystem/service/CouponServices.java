package com.couponproject.CouponManagmentSystem.service;

import com.couponproject.CouponManagmentSystem.core.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponServices {

    Coupon addNewCoupon(Coupon coupon);
    Coupon updateCoupon(Coupon coupon);
    Optional<Coupon> getCouponById(Long couponId);
    List<Coupon> getAllCoupons();
    void deleteCouponById(Long couponId);
    void deleteAllCoupons();

}


