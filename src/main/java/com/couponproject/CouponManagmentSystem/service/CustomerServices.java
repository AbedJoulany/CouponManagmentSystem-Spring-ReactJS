package com.couponproject.CouponManagmentSystem.service;

import com.couponproject.CouponManagmentSystem.core.Category;
import com.couponproject.CouponManagmentSystem.core.Coupon;
import com.couponproject.CouponManagmentSystem.core.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerServices {

    Customer addNewCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Optional<Customer> getCustomerById(Long customerId);
    List<Customer> getAllCustomers();
    void deleteCustomerById(Long customerId);
    void deleteAllCustomers();

    Optional<Customer> getCustomerDetails(String email);

    List<Coupon> getCustomerCoupons(Long customerId);
    List<Coupon> getCustomerCoupons(Long customerId, Category category);
    List<Coupon> getCustomerCoupons(Long customerId, double maxPrice);

    void purchaseCoupon(Long customerId,Coupon coupon) throws SQLException;

    Optional<Customer> getCustomerByEmail(String email);
}
