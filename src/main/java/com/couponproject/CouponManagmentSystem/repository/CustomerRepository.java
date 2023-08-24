package com.couponproject.CouponManagmentSystem.repository;

import com.couponproject.CouponManagmentSystem.core.Company;
import com.couponproject.CouponManagmentSystem.core.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM customers WHERE email=?1", nativeQuery = true)
    Optional<Customer> findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE customers SET first_name = :#{#customer.firstName}," +
            " last_name = :#{#customer.lastName}, email = :#{#customer.email}," +
            " password = :#{#customer.password} WHERE id = :#{#customer.id}", nativeQuery = true)
    public void updateCustomer(@Param("customer") Customer customer);

}
