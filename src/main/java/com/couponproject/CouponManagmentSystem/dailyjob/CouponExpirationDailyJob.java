package com.couponproject.CouponManagmentSystem.dailyjob;

import com.couponproject.CouponManagmentSystem.core.Coupon;
import com.couponproject.CouponManagmentSystem.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@EnableScheduling
@EnableAsync
@Component
@Scope("singleton")
public class CouponExpirationDailyJob implements Runnable {
    @Autowired
    private CouponRepository couponRepository;
    final static int MILLIS_TO_DAY = 24 * 60 * 60 * 1000;
    private boolean running = true;

    @Async
    @Scheduled(fixedDelay = MILLIS_TO_DAY)
    public void run() {
        if (!running) {
            return; // Skip execution
        }
        try {
            List<Coupon> couponList = couponRepository.findAll();
            for (Coupon coupon : couponList) {
                if (coupon.getEndDate().before(new java.util.Date())) {
                    couponRepository.deleteCouponPurchase(coupon.getId());
                    couponRepository.deleteCoupon(coupon.getId());
                }
            }
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }
}

