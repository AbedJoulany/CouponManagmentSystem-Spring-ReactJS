package com.couponproject.CouponManagmentSystem.controller;

import com.couponproject.CouponManagmentSystem.auth.AuthenticationService;
import com.couponproject.CouponManagmentSystem.auth.RegisterRequest;
import com.couponproject.CouponManagmentSystem.core.Company;
import com.couponproject.CouponManagmentSystem.core.Customer;
import com.couponproject.CouponManagmentSystem.core.Role;
import com.couponproject.CouponManagmentSystem.service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminServices service;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value="/addCompany")
    public ResponseEntity<Void> addCompany( @RequestBody Company company) throws SQLException {
        var newCompany = RegisterRequest.builder()
                .firstname(company.getName())
                .lastname(company.getName())
                .email(company.getEmail())
                .password(company.getPassword())
                .role(Role.Company)
                .build();
        authenticationService.register(newCompany);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value="/updateCompany")
    public ResponseEntity<Void> updateCompany(@RequestBody Company company) throws SQLException {
        System.out.println("company: "+company);
        var newCompany = RegisterRequest.builder()
                .id(company.getId())
                .firstname(company.getName())
                .firstname(company.getName())
                .lastname(company.getName())
                .email(company.getEmail())
                .password(company.getPassword())
                .role(Role.Company)
                .build();
        authenticationService.update(newCompany);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value="/deleteCompany")
    public ResponseEntity<Void> deleteCompany(@RequestParam Long id) throws SQLException {
        service.deleteCompany(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value="/getAllCompanies")
    public List<Company> getAllCompanies() {
        return service.getAllCompanies();
    }
    @GetMapping("/getOneCompany")
    public Optional<Company> getOneCompany(@RequestParam Long id) {
        return service.getCompanyById(id);
    }
    @PostMapping(value="/addCustomer")
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) throws SQLException {
        var newCustomer = RegisterRequest.builder()
                .firstname(customer.getFirstName())
                .lastname(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .role(Role.Customer)
                .build();
        authenticationService.register(newCustomer);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value="/updateCustomer")
    public ResponseEntity<Void> updateCustomer(@RequestBody Customer customer) throws SQLException {
        var newCustomer = RegisterRequest.builder()
                .firstname(customer.getFirstName())
                .lastname(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .role(Role.Customer)
                .build();
        authenticationService.update(newCustomer);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value="/deleteCustomer")
    public ResponseEntity<Void> deleteCustomer(@RequestParam Long id) throws SQLException {
        service.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value="/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = service.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
    @GetMapping("/getOneCustomer")
    public Optional<Customer> getOneCustomer(@RequestParam Long id) {
        return service.getCustomerById(id);
    }
}
