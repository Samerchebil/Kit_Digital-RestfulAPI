package com.example.kit_digital;

import com.example.kit_digital.Entity.Customer;
import com.example.kit_digital.Entity.Role;
import com.example.kit_digital.Service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class KitDigitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(KitDigitalApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/api/auth/**").antMatchers("/v3/api-docs/**").antMatchers("configuration/**").antMatchers("/swagger*/**").antMatchers("/webjars/**").antMatchers("/swagger-ui/**");
    }
    @Bean
    CommandLineRunner run(CustomerService userService) {
        return args -> {
//            userService.saveRole(new Role(null, "ROLE_USER"));
//            userService.saveRole(new Role(null, "ROLE_ADMIN"));
//          userService.saveCustomer(new Customer("messi","leo messi","Tunisien(e) résidant(e) en Tunisie","messi@gmail.com","civility","2022-09-26T10:25:49.771Z",true,"0000"));
        };
    }
}
