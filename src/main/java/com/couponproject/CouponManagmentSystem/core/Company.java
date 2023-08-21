package com.couponproject.CouponManagmentSystem.core;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


//======== lombok
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMPANIES")
//=======
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
}
