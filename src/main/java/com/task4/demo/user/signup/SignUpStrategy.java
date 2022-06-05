package com.task4.demo.user.signup;

import com.task4.demo.repositories.UserRepository;

public interface SignUpStrategy {
    Object signUp(UserRepository repository) throws RuntimeException;

    SignUpStrategy setSignUpEntity(SignUpEntity entity);
}
