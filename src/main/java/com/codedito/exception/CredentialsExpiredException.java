package com.codedito.exception;

import org.springframework.security.core.AuthenticationException;

public class CredentialsExpiredException extends AuthenticationException {

    public CredentialsExpiredException(String message) {
        super(message);
    }
}
