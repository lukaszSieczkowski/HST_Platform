package com.codedito.exception;

import org.springframework.security.core.AuthenticationException;

public class EnabledUserException extends AuthenticationException {

    public EnabledUserException(String message) {
        super(message);
    }
}
