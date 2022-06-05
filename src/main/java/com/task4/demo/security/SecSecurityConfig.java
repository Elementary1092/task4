package com.task4.demo.security;

import com.task4.demo.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .logout()
                .logoutUrl("/logout")
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login.html")
                .and()
                .build();
    }
}
