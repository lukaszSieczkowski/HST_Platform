package com.codedito.exception;

import org.springframework.security.core.AuthenticationException;

public class LockedAccountException extends AuthenticationException {

    public LockedAccountException(String message) {
        super(message);
    }
}
