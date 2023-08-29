package com.couponproject.CouponManagmentSystem.repository;

import com.couponproject.CouponManagmentSystem.core.Category;
import com.couponproject.CouponManagmentSystem.core.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    @Query(value="select * from customers_vs_coupons c  where c.CUSTOMER_ID = ?1" , nativeQuery = true)
    List<Coupon> findPurchaseByCustomerID(Long CUSTOMER_ID);

    @Query(value = "DELETE FROM customers_vs_coupons WHERE coupon_id=?1",
    nativeQuery = true)
    void deleteCouponPurchaseById(Long couponID);
    @Query(value = "SELECT * FROM coupons WHERE COMPANY_ID = :companyId", nativeQuery = true)
    List<Coupon> getByCompanyID(@Param("companyId") Long id);
    @Query(value = "SELECT * FROM coupons WHERE id=?1", nativeQuery = true)
    Coupon getCouponById(Long id);
    @Query(value = "UPDATE coupons SET company_id=:companyId, category=:category, title=:title," +
            " description=:description, start_date=:startDate, end_date=:endDate," +
            " amount=:amount, price=:price, image=:image WHERE id=:id", nativeQuery = true)
    void updateCoupon(@Param("companyId") Long companyId, @Param("category") Category category,
                      @Param("title") String title, @Param("description") String description,
                      @Param("startDate") Date startDate, @Param("endDate") Date endDate,
                      @Param("amount") int amount, @Param("price") double price,
                      @Param("image") String image, @Param("id") Long id);

    @Query(value = "DELETE FROM coupons WHERE id=?1", nativeQuery = true)
    void deleteCoupon(Long couponID);
    @Query(value="DELETE FROM customers_vs_coupons WHERE coupon_id=?1" , nativeQuery = true)
    void deleteCouponPurchase(Long couponID);
    @Query(value = "SELECT * FROM coupons WHERE COMPANY_ID=?1 and CATEGORY=?2", nativeQuery = true)
    List<Coupon> getAllCouponsByCompanyIdAndCategory(Long companyID, Category category);
    @Query(value = "SELECT * FROM coupons WHERE COMPANY_ID=?1 and PRICE <=?2", nativeQuery = true)
    List<Coupon> getAllCouponsByCompanyIdAndMaxPrice(Long companyID, double maxPrice);
    @Query(value = "SELECT * FROM coupons WHERE CATEGORY=?2 and id="+
            "(select COUPON_ID from customers_vs_coupons where CUSTOMER_ID = ?1)", nativeQuery = true)
    List<Coupon> getAllCouponsByCustomerIdAndCategory(Long customerId, Category category);
    @Query(value="SELECT * FROM coupons WHERE PRICE <=?2 and id ="+
            "(select COUPON_ID from customers_vs_coupons where CUSTOMER_ID = ?1)", nativeQuery = true)
    List<Coupon> getAllCouponsByCustomerIdAndMaxPrice(Long customerId, double maxPrice);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO customers_vs_coupons(customer_id, coupon_id) VALUES(:CUSTOMER_ID, :COUPON_ID)", nativeQuery = true)
    void addCouponPurchase(@Param("CUSTOMER_ID") Long CUSTOMER_ID, @Param("COUPON_ID") Long COUPON_ID);
    @Transactional
    @Modifying
    @Query(value="update coupons c set c.AMOUNT = ?1 where c.id = ?2" , nativeQuery = true)
    void updateCouponAmount(int newAmount, Long id);
    @Query(value="select * from coupons c where c.company_id = ?1" , nativeQuery = true)
    List<Coupon> findByCompanyID(Long companyId);
    @Transactional
    @Modifying
    @Query(value="delete from customers_vs_coupons c  where c.CUSTOMER_ID = ?1" , nativeQuery = true)
    void deleteCouponPurchaseByCustomerID(Long customerID);
}
