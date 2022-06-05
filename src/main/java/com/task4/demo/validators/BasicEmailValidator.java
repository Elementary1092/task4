package com.task4.demo.validators;

import com.task4.demo.exceptions.InvalidEmailException;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class BasicEmailValidator implements EmailValidator {
    private final Pattern pattern = Pattern.compile(
            "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$"
    );

    private String email;

    public BasicEmailValidator() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email.trim();
    }

    @Override
    public void validate() throws InvalidEmailException {
        if (!pattern.matcher(email).matches()) {
            throw new InvalidEmailException(email);
        }
    }

    @Override
    public void validate(String email) throws InvalidEmailException {
        if (!pattern.matcher(email.trim()).matches()) {
            throw new InvalidEmailException(email);
        }
    }
}
