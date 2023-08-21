package com.couponproject.CouponManagmentSystem.controller;

import com.couponproject.CouponManagmentSystem.auth.AuthenticationService;
import com.couponproject.CouponManagmentSystem.auth.RegisterRequest;
import com.couponproject.CouponManagmentSystem.core.Company;
import com.couponproject.CouponManagmentSystem.core.Customer;
import com.couponproject.CouponManagmentSystem.core.Role;
import com.couponproject.CouponManagmentSystem.service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('Administration')")
public class AdminController {
    @Autowired
    AdminServices service;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value="/addCompany")
    public void addCompany( @RequestBody Company company) throws SQLException {
        var newCompany = RegisterRequest.builder()
                .firstname(company.getName())
                .lastname(company.getName())
                .email(company.getEmail())
                .password(company.getPassword())
                .role(Role.Company)
                .build();
        authenticationService.register(newCompany);
        //service.addCompany(company);
    }
    @PutMapping(value="/updateCompany")
    public void updateCompany(@RequestBody Company company) throws SQLException {
        service.updateCompany(company);
    }
    @DeleteMapping(value="/deleteCompany")
    public void deleteCompany(@RequestParam Long companyID) throws SQLException {
        service.deleteCompany(companyID);
    }
    @GetMapping(value="/getAllCompanies")
    public List<Company> getAllCompanies() {
        return service.getAllCompanies();
    }
    @GetMapping("/getOneCompany")
    public Optional<Company> getOneCompany(@RequestParam Long companyID) {
        return service.getCompanyById(companyID);
    }
    @PostMapping(value="/addCustomer")
    public void addCustomer(@RequestBody Customer customer) throws SQLException {
        var newCustomer = RegisterRequest.builder()
                .firstname(customer.getFirstName())
                .lastname(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .role(Role.Customer)
                .build();
        authenticationService.register(newCustomer);
    }
    @PutMapping(value="/updateCustomer")
    public void updateCustomer(@RequestBody Customer customer) throws SQLException {
        service.updateCustomer(customer);
    }
    @DeleteMapping(value="/deleteCustomer")
    public void deleteCustomer(@RequestParam Long customerID) throws SQLException {
        service.deleteCustomer(customerID);
    }
    @GetMapping(value="/getAllCustomers")
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }
    @GetMapping("/getOneCustomer")
    public Optional<Customer> getOneCustomer(@RequestParam Long customerID) {
        return service.getCustomerById(customerID);
    }
}
