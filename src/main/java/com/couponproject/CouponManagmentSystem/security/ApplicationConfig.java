package com.couponproject.CouponManagmentSystem.security;

import com.couponproject.CouponManagmentSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

   /* @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomerRepository customerRepository;*/

    @Bean
    public UserDetailsService userDetailsService(){
/*        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));*/

        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                System.out.println("loadUserByUsername: "+username);
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
            }
        };

        /*return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

                if(email.equals("admin@admin.com")){
                    return User.builder()
                            .email(email)
                            .password(passwordEncoder().encode("admin"))
                            .role(Role.Administrator)
                            .build();
                }

                Company company = companyRepository.findByEmail(email);
                if (company != null) {
                    return User.builder()
                            .id(company.getId())
                            .email(company.getEmail())
                            .password(company.getPassword())
                            .role(Role.Company)
                            .build();
                }
                Customer customer = customerRepository.findByEmail(email);
                if (customer != null) {
                    return User.builder()
                            .id(customer.getId())
                            .email(customer.getEmail())
                            .password(customer.getPassword())
                            .role(Role.Customer)
                            .build();
                }

                throw new UsernameNotFoundException("User with email " + email + " not found");
            }
        };*/
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
     return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
