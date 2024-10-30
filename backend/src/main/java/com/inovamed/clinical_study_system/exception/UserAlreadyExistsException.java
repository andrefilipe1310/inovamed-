package com.inovamed.clinical_study_system.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(){super("User already exists");}
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
