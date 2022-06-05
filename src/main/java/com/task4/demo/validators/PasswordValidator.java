package com.task4.demo.validators;

public interface PasswordValidator {
    void validate() throws RuntimeException;

    void validate(String toBeValidated) throws RuntimeException;
}
