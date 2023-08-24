package com.couponproject.CouponManagmentSystem.controller;

import com.couponproject.CouponManagmentSystem.core.*;
import com.couponproject.CouponManagmentSystem.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/customer")
public class CustomerController{

    @Autowired
    CustomerServices service;

    @PostMapping(value="/purchaseCoupon")
    public void purchaseCoupon(@RequestBody Coupon coupon, Authentication authentication) throws SQLException {
        Customer customer = getCustomer(authentication);
        service.purchaseCoupon(customer.getId(), coupon);
    }

    @GetMapping(value="/getCustomerCoupons")
    public List<Coupon> getCustomerCoupons(Authentication authentication) {
        Customer customer = getCustomer(authentication);
        return service.getCustomerCoupons(customer.getId());
    }

    @GetMapping(value="/getCustomerCouponsByCategory")
    public List<Coupon> getCustomerCoupons(@RequestParam Category category, Authentication authentication) {
        Customer customer = getCustomer(authentication);
        return service.getCustomerCoupons(customer.getId(), category);
    }

    @GetMapping(value="/getCustomerCouponsByMaxPrice")
    public List<Coupon> getCustomerCoupons(@RequestParam double maxPrice, Authentication authentication){
        Customer customer = getCustomer(authentication);
        return service.getCustomerCoupons(customer.getId(), maxPrice);
    }

    @GetMapping(value="/getCustomerDetails")
    public Optional<Customer> getCustomerDetails(Authentication authentication) {
        Customer customer = getCustomer(authentication);
        return service.getCustomerDetails(customer.getEmail());
    }
    private Customer getCustomer(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Optional<Customer> customerOptional = service.getCustomerById(currentUser.getId());
        return customerOptional.orElse(null);
    }
}
