package com.inovamed.clinical_study_system.exception;

public class SignatureErrorVerifyException extends RuntimeException {
    public SignatureErrorVerifyException(String message) {
        super(message);
    }
    public SignatureErrorVerifyException(Exception e) { super("Error verifying signature.");}
}
