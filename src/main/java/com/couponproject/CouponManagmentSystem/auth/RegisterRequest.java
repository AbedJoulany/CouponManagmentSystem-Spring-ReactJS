package com.couponproject.CouponManagmentSystem.auth;

import com.couponproject.CouponManagmentSystem.core.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}
