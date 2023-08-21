package com.couponproject.CouponManagmentSystem.repository;

import com.couponproject.CouponManagmentSystem.core.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value="SELECT * FROM companies WHERE email=?1 and password=?2", nativeQuery = true)
    Company findByEmailAndPassword(String email, String password);
    @Query(value="SELECT * FROM companies WHERE name=?1", nativeQuery = true)
    Company findByNameExists(String name);
    @Query(value="SELECT * FROM companies WHERE email=?1", nativeQuery = true)
    Optional<Company> findByEmail(String email);
}
