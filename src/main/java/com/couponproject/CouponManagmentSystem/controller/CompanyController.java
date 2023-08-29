package com.couponproject.CouponManagmentSystem.controller;

import com.couponproject.CouponManagmentSystem.core.Category;
import com.couponproject.CouponManagmentSystem.core.Company;
import com.couponproject.CouponManagmentSystem.core.Coupon;
import com.couponproject.CouponManagmentSystem.core.User;
import com.couponproject.CouponManagmentSystem.service.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/company")
public class CompanyController{
    @Autowired
    CompanyServices service;
    @PostMapping(value="/addCoupon")
    public void addCoupon(@RequestBody Coupon newCoupon, Authentication authentication) throws SQLException {
        Company company = getCompany(authentication);
        service.addCoupon(company.getId(), newCoupon);
    }
    @PutMapping(value="/updateCoupon")
    public void updateCoupon(@RequestBody Coupon updetedCoupon,Authentication authentication) throws SQLException {
        updetedCoupon.setCompany(getCompany(authentication));
        service.updateCoupon(updetedCoupon);
    }

    @DeleteMapping(value="/deleteCoupon")
    public void deleteCoupon(@RequestParam Long id) throws SQLException {
        service.deleteCoupon(id);
    }

    @GetMapping(value="/getCompanyCoupons")
    public List<Coupon> getCompanyCoupons(Authentication authentication) {
        Company company = getCompany(authentication);
        return service.getCompanyCoupons(company.getId());
    }
    @GetMapping(value="/getCouponsByCategory")
    public List<Coupon> getCompanyCoupons(@RequestParam Category category, Authentication authentication) {
        Company company = getCompany(authentication);
        return service.getCompanyCoupons(company.getId(), category);
    }
    @GetMapping(value="/getCouponsUpToMaxPrice")
    public List<Coupon> getCompanyCoupons(@RequestParam double maxPrice,Authentication authentication) {
        Company company = getCompany(authentication);
        return service.getCompanyCoupons(company.getId(), maxPrice);
    }
    @GetMapping(value="/getCompanyDetails")
    public Optional<Company> getCompanyDetails(Authentication authentication) {
        Company company = getCompany(authentication);
        return service.getCompanyDetails(company.getEmail());
    }

    private Company getCompany(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Optional<Company> companyOptional = service.getCompanyByEmail(currentUser.getEmail());
        return companyOptional.orElse(null);
    }

}
