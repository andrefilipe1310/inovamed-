package com.inovamed.clinical_study_system.exception;

public class SignatureNotFoundException extends RuntimeException {
    public SignatureNotFoundException(String message) {
        super(message);
    }
    public SignatureNotFoundException() { super("Signature not found.");}
}
