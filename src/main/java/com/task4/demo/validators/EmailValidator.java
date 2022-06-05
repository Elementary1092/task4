package com.task4.demo.validators;

public interface EmailValidator {
    void validate() throws RuntimeException;

    void validate(String email) throws RuntimeException;
}
