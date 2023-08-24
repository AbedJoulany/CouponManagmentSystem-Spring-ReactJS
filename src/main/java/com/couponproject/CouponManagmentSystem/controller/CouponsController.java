package com.couponproject.CouponManagmentSystem.controller;

import com.couponproject.CouponManagmentSystem.core.Company;
import com.couponproject.CouponManagmentSystem.core.Coupon;
import com.couponproject.CouponManagmentSystem.service.CouponServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class CouponsController {

    @Autowired
    CouponServices service;

    @GetMapping(value="/getAllCoupons")
    public ResponseEntity<List<Coupon>> getAllCoupons() throws SQLException {
        return ResponseEntity.ok(service.getAllCoupons());
    }
}
