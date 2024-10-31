package com.inovamed.clinical_study_system.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException(){super("Email Already Registered Exception.");}
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
