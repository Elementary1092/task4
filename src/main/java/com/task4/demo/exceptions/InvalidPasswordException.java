package com.task4.demo.exceptions;

public class InvalidPasswordException extends RuntimeException
        implements InvalidLoginParametersException {
    public InvalidPasswordException() {
        super("incorrect password");
    }
}
