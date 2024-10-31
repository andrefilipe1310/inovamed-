package com.inovamed.clinical_study_system.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){super("User not found.");}
    public UserNotFoundException(String message) {
        super(message);
    }
}
