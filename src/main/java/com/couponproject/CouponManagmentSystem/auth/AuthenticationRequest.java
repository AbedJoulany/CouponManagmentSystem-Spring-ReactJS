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
public class AuthenticationRequest {
    private String email;
    private String password;
    private Role role;
}
