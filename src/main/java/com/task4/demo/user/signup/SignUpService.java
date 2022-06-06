package com.task4.demo.user.signup;

import com.task4.demo.repositories.UserRepository;
import com.task4.demo.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.stereotype.Service;

@Service
@DependsOnDatabaseInitialization
public class SignUpService {
    private SignUpStrategy signUpStrategy;

    private UserRepository repository;

    @Autowired
    public SignUpService(UserRepository repository) {
        this.repository = repository;
    }

    public void setSignUpStrategy(SignUpStrategy strategy) {
        this.signUpStrategy = strategy;
    }

    public User registerUser(SignUpEntity entity) throws RuntimeException {
        return (User) signUpStrategy.setSignUpEntity(entity).signUp(repository);
    }
}
