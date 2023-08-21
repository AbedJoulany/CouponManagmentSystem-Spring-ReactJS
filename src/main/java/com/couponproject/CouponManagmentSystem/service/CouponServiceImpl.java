package com.couponproject.CouponManagmentSystem.service;

import com.couponproject.CouponManagmentSystem.core.Coupon;
import com.couponproject.CouponManagmentSystem.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponServices{

    @Autowired
    CouponRepository couponRepository;

    @Override
    public Coupon addNewCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Optional<Coupon> getCouponById(Long couponId) {
        return couponRepository.findById(couponId);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public void deleteCouponById(Long couponId) {
        couponRepository.deleteById(couponId);
    }

    @Override
    public void deleteAllCoupons() {
        couponRepository.deleteAll();
    }
}
