package com.task4.demo.security;

import com.task4.demo.common.PasswordEncoder;
import com.task4.demo.user.UserService;
import com.task4.demo.user.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecSecurityConfig {
    private UserDetailsService userDetailsService;

    @Autowired
    public SecSecurityConfig(UserService userService) {
        this.userDetailsService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .logout()
                .logoutUrl("/logout")
                .and()
                .formLogin()
                .loginPage("/login.html")
                .successForwardUrl("/users")
                .failureForwardUrl("/error")
                .and()
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(new PasswordEncoder());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }
}
