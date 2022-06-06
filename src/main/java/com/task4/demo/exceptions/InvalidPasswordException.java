package com.task4.demo.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidPasswordException extends AuthenticationException
        implements InvalidLoginParametersException {
    public InvalidPasswordException() {
        super("invalid password");
    }

    public InvalidPasswordException(String supplement) {
        super("incorrect password; " + supplement);
    }
}
