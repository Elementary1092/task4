package com.task4.demo.validators;

import com.task4.demo.exceptions.InvalidEmailException;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class BasicEmailValidator implements EmailValidator {
    private final Pattern pattern = Pattern.compile(
            "^[a-zA-Z0-9_-]+(?:\\\\.[a-zA-Z0-9_!-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$"
    );

    private String email;

    public BasicEmailValidator() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    @Override
    public void validate() throws InvalidEmailException {
        if (!pattern.matcher(email).matches()) {
            throw new InvalidEmailException(email);
        }
    }

    @Override
    public void validate(String email) throws InvalidEmailException {
        if (!pattern.matcher(email).matches()) {
            throw new InvalidEmailException(email);
        }
    }
}
