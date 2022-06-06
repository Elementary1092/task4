package com.task4.demo.user.login;

import com.task4.demo.repositories.UserRepository;
import com.task4.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@DependsOnDatabaseInitialization
public class LoginService {
    private UserRepository repository;

    private LoginStrategy loginStrategy;

    @Autowired
    public LoginService(UserRepository repository) {
        this.repository = repository;
    }

    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    public User login(UserLoginDetails user) throws RuntimeException {
        User userDetails = (User) loginStrategy.setLoginEntity(user).login(repository);
        userDetails.setLastSeen(Date.valueOf(LocalDate.now())).setIsOnline(true);
        return userDetails;
    }
}
