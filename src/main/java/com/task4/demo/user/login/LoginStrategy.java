package com.task4.demo.user.login;

import com.task4.demo.repositories.UserRepository;
import com.task4.demo.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginStrategy {
    LoginStrategy setLoginEntity(User user) throws RuntimeException;

    UserDetails login(UserRepository repository) throws RuntimeException;
}
