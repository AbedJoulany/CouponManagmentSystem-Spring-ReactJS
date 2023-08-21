package com.couponproject.CouponManagmentSystem.service;


import com.couponproject.CouponManagmentSystem.core.Company;
import com.couponproject.CouponManagmentSystem.core.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
public interface AdminServices{
    /**
     * COMPANY methods
     */
    void addCompany(Company company) throws SQLException;

    void updateCompany(Company company) throws SQLException;

    void deleteCompany(Long companyId) throws SQLException;

    List<Company> getAllCompanies();

    Optional<Company> getCompanyById(Long companyID);

    /**
     * CUSTOMER methods
     */
    void addCustomer(Customer customer) throws SQLException;

    void updateCustomer(Customer customer) throws SQLException;

    void deleteCustomer(Long customerID) throws SQLException;

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Long customerId);

    String email = "admin@admin.com";
    String password = "admin";
}
