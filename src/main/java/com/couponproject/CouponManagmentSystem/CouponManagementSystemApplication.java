package com.couponproject.CouponManagmentSystem;

import com.couponproject.CouponManagmentSystem.auth.AuthenticationService;
import com.couponproject.CouponManagmentSystem.auth.RegisterRequest;
import com.couponproject.CouponManagmentSystem.core.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class CouponManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponManagementSystemApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@admin.com")
					.password("admin")
					.role(Role.Administrator)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());
		};
	}
}
