package com.couponproject.CouponManagmentSystem.service;

import com.couponproject.CouponManagmentSystem.core.Company;
import com.couponproject.CouponManagmentSystem.core.Customer;
import com.couponproject.CouponManagmentSystem.core.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final AdminServices adminService;

    public void registerCompany(User user) throws SQLException {

        System.out.println("registerCompany: "+ user);
        adminService.addCompany(new Company(user.getId(),
                user.getFirstname(),
                user.getEmail(),
                user.getPassword()));
    }

    public void registerCustomer(User user) throws SQLException {
        adminService.addCustomer(new Customer(user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword()));
    }

    public void updateCompany(User user) throws SQLException {
        adminService.updateCompany(new Company(user.getId(),
                user.getFirstname(),
                user.getEmail(),
                user.getPassword()));
    }

    public void updateCustomer(User user) throws SQLException {
        adminService.updateCustomer(new Customer(user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword()));
    }
}
