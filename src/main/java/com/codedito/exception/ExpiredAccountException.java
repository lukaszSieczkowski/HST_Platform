package com.codedito.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiredAccountException extends AuthenticationException {
    public ExpiredAccountException(String message) {
        super(message);
    }
}
