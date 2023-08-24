package com.couponproject.CouponManagmentSystem.service;


import com.couponproject.CouponManagmentSystem.core.Company;
import com.couponproject.CouponManagmentSystem.core.Coupon;
import com.couponproject.CouponManagmentSystem.core.Customer;
import com.couponproject.CouponManagmentSystem.repository.CompanyRepository;
import com.couponproject.CouponManagmentSystem.repository.CouponRepository;
import com.couponproject.CouponManagmentSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServicesImpl  implements AdminServices {

    private CompanyRepository companyRepository;
    private CouponRepository couponRepository;
    private CustomerRepository customerRepository;
    @Autowired
    public AdminServicesImpl(CompanyRepository companyRepository, CouponRepository couponRepository,
                             CustomerRepository customerRepository) {
        super();
        this.companyRepository = companyRepository;
        this.couponRepository = couponRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void addCompany(Company company) throws SQLException {
        System.out.println("addCompany: "+ company);
        System.out.println(companyRepository.findByEmailAndPassword(company.getEmail(),
                company.getPassword()));

        if(companyRepository.findByEmailAndPassword(company.getEmail(),
                company.getPassword()) ==null&&
                companyRepository.findByNameExists(company.getName())==null)
        {
            companyRepository.save(company);
        }
        else
            throw new SQLException("name or email of the company already exists");
    }
    @Override
    public void updateCompany(Company company) throws SQLException {

        Optional<Company> c = companyRepository.findById(company.getId());
        if(c.isPresent() && c.get().getName().equals(company.getName()))
        {
            companyRepository.save(company);
        }
        else
            throw new SQLException("company_id and company name can`t be updated");
    }
    @Override
    public void deleteCompany(Long companyId) throws SQLException {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isEmpty())
            throw new SQLException("company isn`t found");

        List<Coupon> couponList = couponRepository.findByCompanyID(companyId);

        for (Coupon coupon : couponList) {
            couponRepository.deleteCouponPurchaseById(coupon.getId());
            couponRepository.deleteById(coupon.getId());
        }
        companyRepository.deleteById(companyId);
    }
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    @Override
    public Optional<Company> getCompanyById(Long companyID) {
        return companyRepository.findById(companyID);
    }
    @Override
    public void addCustomer(Customer customer) throws SQLException {

        if(customerRepository.findByEmailAndPassword(customer.getEmail(), customer.getPassword()) ==null)
        {
            customerRepository.save(customer);
        }
        else
            throw new SQLException("email of the customer already exists");
    }
    @Override
    public void updateCustomer(Customer customer) throws SQLException {

        Optional<Customer> c = customerRepository.findById(customer.getId());
        if(c.isPresent())
        {
            customerRepository.save(customer);
        }
        else
            throw new SQLException("customer_id can`t be updated");
    }
    @Override
    public void deleteCustomer(Long customerID) throws SQLException {

        Optional<Customer> c = customerRepository.findById(customerID);
        if (c.isEmpty())
            throw new SQLException("customer isn`t found");

        couponRepository.deleteCouponPurchaseByCustomerID(customerID);
        customerRepository.deleteById(customerID);
    }
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

}
