package com.task4.demo.user.login;

import com.task4.demo.repositories.UserRepository;
import com.task4.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
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

    public User login(User user) throws RuntimeException {
        return (User) loginStrategy.setLoginEntity(user).login(repository);
    }
}
