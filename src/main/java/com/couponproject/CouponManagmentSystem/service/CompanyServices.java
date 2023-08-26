package com.couponproject.CouponManagmentSystem.service;


import com.couponproject.CouponManagmentSystem.core.Category;
import com.couponproject.CouponManagmentSystem.core.Company;
import com.couponproject.CouponManagmentSystem.core.Coupon;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CompanyServices {

    public Optional<Company> getCompanyById(Long companyID);
    public List<Company> getCompanies();
    public void addCoupon(Long companyID, Coupon newCoupon) throws SQLException;
    public Company addNewCompany(Company company);
    public Company updateCompany(Company company);
    public void deleteCompanyById(Long companyId);
    public void deleteAllCompanies();
    void updateCoupon(Coupon updetedCoupon) throws SQLException;
    void deleteCoupon(Long couponID) throws SQLException;
    List<Coupon> getCompanyCoupons(Long companyID);
    public List<Coupon> getCompanyCoupons(Long companyID, Category category);
    public List<Coupon> getCompanyCoupons(Long companyID, double maxPrice);
    Optional<Company> getCompanyDetails(String email);

    Optional<Company> getCompanyByEmail(String email);
}
