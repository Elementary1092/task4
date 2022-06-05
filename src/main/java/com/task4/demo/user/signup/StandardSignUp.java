package com.task4.demo.user.signup;

import com.task4.demo.common.Encoder;
import com.task4.demo.common.PasswordEncoder;
import com.task4.demo.exceptions.InvalidPasswordException;
import com.task4.demo.exceptions.UserAlreadyExistsException;
import com.task4.demo.repositories.UserRepository;
import com.task4.demo.user.User;
import com.task4.demo.validators.BasicEmailValidator;
import com.task4.demo.validators.BasicPasswordValidator;
import com.task4.demo.validators.EmailValidator;
import com.task4.demo.validators.PasswordValidator;
import org.jetbrains.annotations.NotNull;

public class StandardSignUp implements SignUpStrategy {
    private SignUpEntity entity;

    private final PasswordValidator passwordValidator = new BasicPasswordValidator();

    private final EmailValidator emailValidator = new BasicEmailValidator();

    private final Encoder encoder = new PasswordEncoder();

    public StandardSignUp() {}

    @Override
    public Object signUp(UserRepository repository) throws RuntimeException {
        validateSignUpData(entity);

        User user = new User()
                .setEmail(entity.getUsername())
                .setPassword(encoder.encode(entity.getPassword()));

        doesUserExist(user, repository);

        return repository.save(user);
    }

    public void validateSignUpData(SignUpEntity entity) throws RuntimeException {
        emailValidator.validate(entity.getUsername());
        passwordValidator.validate(entity.getPassword());

        if (!entity.getPassword().equals(entity.getConfirmationPassword())) {
            throw new InvalidPasswordException("passwords does not match");
        }
    }

    private User doesUserExist(@NotNull User user, UserRepository repository) throws RuntimeException {
        if (repository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        return user;
    }


    public SignUpEntity getEntity() {
        return entity;
    }

    @Override
    public SignUpStrategy setSignUpEntity(SignUpEntity entity) {
        this.entity = entity;

        return this;
    }
}
