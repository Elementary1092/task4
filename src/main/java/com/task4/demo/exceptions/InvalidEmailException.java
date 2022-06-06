package com.task4.demo.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidEmailException extends AuthenticationException
        implements InvalidLoginParametersException {
    public InvalidEmailException() {
        super("invalid email");
    }

    public InvalidEmailException(String email) {
        super(email + " format is not valid");
    }
}
