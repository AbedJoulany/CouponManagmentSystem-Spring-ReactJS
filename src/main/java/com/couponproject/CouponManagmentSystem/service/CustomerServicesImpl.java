package com.couponproject.CouponManagmentSystem.service;


import com.couponproject.CouponManagmentSystem.core.Category;
import com.couponproject.CouponManagmentSystem.core.Coupon;
import com.couponproject.CouponManagmentSystem.core.Customer;
import com.couponproject.CouponManagmentSystem.core.CustomersVsCoupons;
import com.couponproject.CouponManagmentSystem.repository.CouponRepository;
import com.couponproject.CouponManagmentSystem.repository.CustomerRepository;
import com.couponproject.CouponManagmentSystem.repository.Customers_vs_CouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CustomerServicesImpl  implements  CustomerServices{

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    private Customers_vs_CouponsRepository customers_vs_couponsRepository;

    @Override
    public Customer addNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }
    @Override
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

    @Override
    public Optional<Customer> getCustomerDetails(String email) {
        return customerRepository.findByEmail(email);
    }
    @Override
    public List<Coupon> getCustomerCoupons(Long customerId) {
        List<Coupon> customerCoupons = new ArrayList<>();

        List<CustomersVsCoupons> purchasedCoupons = customers_vs_couponsRepository.findPurchaseByCoustomerID(customerId);

        for (CustomersVsCoupons purchasedCoupon : purchasedCoupons) {
            Coupon coupon = purchasedCoupon.getCoupon();
            customerCoupons.add(couponRepository.getCouponById(coupon.getId()));
        }
        return customerCoupons;
    }
    @Override
    public List<Coupon> getCustomerCoupons(Long customerId, Category category) {
        return couponRepository.getAllCouponsByCustomerIdAndCategory(customerId,category);

    }
    @Override
    public List<Coupon> getCustomerCoupons(Long customerId, double maxPrice) {
        return couponRepository.getAllCouponsByCustomerIdAndMaxPrice(customerId,maxPrice);
    }
    @Override
    public void purchaseCoupon(Long customerId, Coupon coupon) throws SQLException {
        Date currentDate = new Date();
        Optional<CustomersVsCoupons> couponListOptional   = customers_vs_couponsRepository.findPurchaseByCouponIDAndCustomerID(coupon.getId(),customerId);

        boolean alreadyPurchased = false;
        boolean couponAvailable = true;
        boolean couponNotExpired = true;
        if (couponListOptional .isPresent()) {
            alreadyPurchased = true;
            couponAvailable = couponRepository.findById(coupon.getId()).map(Coupon::getAmount).orElse(0) > 0;
            couponNotExpired = coupon.getEndDate().after(currentDate);
        }

        if (!alreadyPurchased && couponAvailable && couponNotExpired)
            {
                couponRepository.addCouponPurchase(customerId, coupon.getId());
                int newAmount = coupon.getAmount() - 1;
                coupon.setAmount(newAmount);
                couponRepository.updateCouponAmount(newAmount, coupon.getId());
            }
        else {
            throw new SQLException("Purchase coupon failed:\n" +
                    (alreadyPurchased ? "Coupon has already been purchased\n" : "") +
                    (!couponAvailable ? "Coupons are out of stock\n" : "") +
                    (!couponNotExpired ? "Coupon has expired\n" : ""));
        }
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
