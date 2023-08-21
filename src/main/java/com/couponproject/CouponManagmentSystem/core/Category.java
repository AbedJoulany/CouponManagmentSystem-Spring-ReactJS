package com.couponproject.CouponManagmentSystem.core;

import jakarta.persistence.*;

@Entity
@Table(name="CATEGORIES")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    // Getters and setters
}
