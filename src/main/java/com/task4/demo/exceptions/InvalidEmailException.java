package com.task4.demo.exceptions;

public class InvalidEmailException extends RuntimeException
        implements InvalidLoginParametersException {
    public InvalidEmailException() {
        super("invalid email");
    }

    public InvalidEmailException(String email) {
        super(email + " format is not valid");
    }
}
