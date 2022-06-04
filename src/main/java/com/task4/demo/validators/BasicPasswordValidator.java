package com.task4.demo.validators;

import com.task4.demo.exceptions.InvalidPasswordException;

public class BasicPasswordValidator implements PasswordValidator {
    private String toBeValidated;

    public BasicPasswordValidator() {}

    public void setPasswordToValidate(String toBeValidated) {
        this.toBeValidated = toBeValidated;
    }

    @Override
    public void validate() throws InvalidPasswordException {
        if (toBeValidated.trim().length() == 0) {
            throw new InvalidPasswordException("expected length of password to be greater than 1");
        }
    }
}
