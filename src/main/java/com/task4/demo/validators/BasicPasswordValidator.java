package com.task4.demo.validators;

public class BasicPasswordValidator implements PasswordValidator {
    private String toBeValidated;

    public BasicPasswordValidator(String toBeValidated) {
        this.toBeValidated = toBeValidated;
    }

    @Override
    public void validate() {
        if (toBeValidated.trim().length() == 0) {

        }
    }
}
