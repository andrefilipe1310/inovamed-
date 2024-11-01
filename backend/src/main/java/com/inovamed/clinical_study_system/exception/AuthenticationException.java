package com.inovamed.clinical_study_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(){super("Authentication Exception.");}
    public AuthenticationException(String message) {
        super(message);
    }
}
