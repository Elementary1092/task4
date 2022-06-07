package com.task4.demo.exceptions;

public class NoSuchUserException extends RuntimeException implements InvalidLoginParametersException {
    public NoSuchUserException() {
        super("Could not find user with such email");
    }

    public NoSuchUserException(String email) {
        super("try signing up with " + email + " email");
    }
}
