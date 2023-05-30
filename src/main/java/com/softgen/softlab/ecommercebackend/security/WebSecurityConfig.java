package com.softgen.softlab.ecommercebackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JWTRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
      http.csrf().disable().cors().disable();
      http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
      http.authorizeHttpRequests()
              .requestMatchers("/product", "/auth/register", "/auth/login", "/auth/verify").permitAll()
              .anyRequest().authenticated();
      return http.build();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }


}
