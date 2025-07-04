package com.giweb.supply.business.segundotalleracm.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
public class DisabledNoSecurityConfig {

    // @Bean
    // @Order(Integer.MIN_VALUE)
    public SecurityFilterChain publicEndpointsFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/setup/**", "/api/auth/**", "/api/hoteles/**", "/api/habitaciones/**")
            .csrf().disable()
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()
            );
        return http.build();
    }
}
