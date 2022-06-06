package com.task4.demo.user.login;

import com.task4.demo.repositories.UserRepository;
import com.task4.demo.user.User;


public interface LoginStrategy {
    LoginStrategy setLoginEntity(UserLoginDetails user) throws RuntimeException;

    User login(UserRepository repository) throws RuntimeException;
}
