package com.task4.demo.user.login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserLoginDetails implements UserDetails {
    private String password;

    private String username;

    private boolean authorized;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return authorized;
    }

    @Override
    public boolean isAccountNonLocked() {
        return authorized;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return authorized;
    }

    @Override
    public boolean isEnabled() {
        return authorized;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorized() {
        this.authorized = true;
    }
}
