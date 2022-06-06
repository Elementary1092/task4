package com.task4.demo.user.login;

import com.task4.demo.common.Encoder;
import com.task4.demo.common.PasswordEncoder;
import com.task4.demo.exceptions.InvalidPasswordException;
import com.task4.demo.repositories.UserRepository;
import com.task4.demo.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;

public class StandardLogin implements LoginStrategy {
    private UserLoginDetails user;

    private final Encoder encoder = new PasswordEncoder();

    @Override
    public LoginStrategy setLoginEntity(@NotNull UserLoginDetails user) throws RuntimeException {
        this.user = user;
        return this;
    }

    @Override
    public UserDetails login(UserRepository repository) throws RuntimeException {
        User userFromDb = repository.findByEmail(user.getUsername());

        if (!encoder.matches(user.getPassword(), userFromDb.getPassword())) {
            throw new InvalidPasswordException();
        }

        return userFromDb;
    }
}
