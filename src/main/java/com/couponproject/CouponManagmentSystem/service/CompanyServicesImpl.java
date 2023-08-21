package com.couponproject.CouponManagmentSystem.service;

import com.couponproject.CouponManagmentSystem.core.Category;
import com.couponproject.CouponManagmentSystem.core.Company;
import com.couponproject.CouponManagmentSystem.core.Coupon;
import com.couponproject.CouponManagmentSystem.repository.CompanyRepository;
import com.couponproject.CouponManagmentSystem.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServicesImpl implements CompanyServices {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Optional<Company> getCompanyById(Long companyID) {
        return companyRepository.findById(companyID);
    }

    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void addCoupon(Long companyID, Coupon newCoupon) throws SQLException {
        List<Coupon> CouponList = couponRepository.getByCompanyID(companyID);

        for (Coupon coupon1 : CouponList) {
            if (newCoupon.getTitle().equals(coupon1.getTitle()))
                throw new SQLException("coupon with same name and same company already exists");
        }
        Date date=Date.valueOf(java.time.LocalDate.now());
        if(date.after(newCoupon.getEndDate())) {
            throw new SQLException("An expired coupon cannot be added !");
        }
        Optional<Company> company = companyRepository.findById(companyID);
        newCoupon.setCompany(company.orElseThrow(() -> new SQLException("Company not found")));
        couponRepository.save(newCoupon);
    }

    @Override
    public Company addNewCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompanyById(Long companyId) {
        companyRepository.deleteById(companyId);
    }

    @Override
    public void deleteAllCompanies() {
        companyRepository.deleteAll();
    }

    @Override
    public void updateCoupon(Coupon updetedCoupon) throws SQLException {
        Coupon coupon1 = couponRepository.getCouponById(updetedCoupon.getId());
        if (coupon1 != null && coupon1.getCompany().getId().equals(updetedCoupon.getCompany().getId())) {
            couponRepository.updateCoupon(updetedCoupon);
        }
        else
            throw new SQLException("coupon_id and company_id can`t be updated");
    }
    @Override
    public void deleteCoupon(Long couponID) throws SQLException {
        if(couponRepository.getCouponById(couponID) == null)
            throw new SQLException("coupon not found");
        couponRepository.deleteCouponPurchase(couponID);
        couponRepository.deleteCoupon(couponID);
    }
    @Override
    public List<Coupon> getCompanyCoupons(Long companyID) {
        return couponRepository.getByCompanyID(companyID);
    }
    @Override
    public List<Coupon> getCompanyCoupons(Long companyID, Category category) {
        return couponRepository.getAllCouponsByCompanyIdAndCategory(companyID,category);
    }
    @Override
    public List<Coupon> getCompanyCoupons(Long companyID, double maxPrice) {
        return couponRepository.getAllCouponsByCompanyIdAndMaxPrice(companyID,maxPrice);
    }
    @Override
    public Optional<Company> getCompanyDetails(String email) {
        return companyRepository.findByEmail(email);
    }
}
