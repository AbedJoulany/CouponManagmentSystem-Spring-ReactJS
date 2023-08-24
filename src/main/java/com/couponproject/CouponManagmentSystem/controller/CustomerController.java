package com.couponproject.CouponManagmentSystem.controller;

import com.couponproject.CouponManagmentSystem.core.*;
import com.couponproject.CouponManagmentSystem.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> purchaseCoupon(@RequestBody Coupon coupon, Authentication authentication) throws SQLException {
        Customer customer = getCustomer(authentication);
        service.purchaseCoupon(customer.getId(), coupon);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value="/purchaseCoupons",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> purchaseCoupons(@RequestBody List<Coupon> coupons, Authentication authentication) throws SQLException {
        Customer customer = getCustomer(authentication);
        for(Coupon coupon: coupons)
        {
            service.purchaseCoupon(customer.getId(), coupon);
        }
        return ResponseEntity.ok().build();
    }
    @GetMapping(value="/getCustomerCoupons")

    public ResponseEntity<List<Coupon>> getCustomerCoupons(Authentication authentication) {
        Customer customer = getCustomer(authentication);
        System.out.println("Customer: "+authentication);
        return ResponseEntity.ok(service.getCustomerCoupons(customer.getId()));
    }

    @GetMapping(value="/getCustomerCouponsByCategory")
    public ResponseEntity<List<Coupon>> getCustomerCoupons(@RequestParam Category category, Authentication authentication) {
        Customer customer = getCustomer(authentication);
        return ResponseEntity.ok(service.getCustomerCoupons(customer.getId(), category));
    }

    @GetMapping(value="/getCustomerCouponsByMaxPrice")
    public ResponseEntity<List<Coupon>> getCustomerCoupons(@RequestParam double maxPrice, Authentication authentication){
        Customer customer = getCustomer(authentication);
        return ResponseEntity.ok(service.getCustomerCoupons(customer.getId(), maxPrice));
    }

    @GetMapping(value="/getCustomerDetails")
    public Optional<Customer> getCustomerDetails(Authentication authentication) {
        Customer customer = getCustomer(authentication);
        return service.getCustomerDetails(customer.getEmail());
    }
    private Customer getCustomer(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Optional<Customer> customerOptional = service.getCustomerByEmail(currentUser.getEmail());
        return customerOptional.orElse(null);
    }
}
