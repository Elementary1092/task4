package com.task4.demo.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("such user already exists");
    }

    public UserAlreadyExistsException(String email) {
        super(email + " is already registered");
    }
}
