package com.inovamed.clinical_study_system.exception;

public class InvalidSignatureValidityException extends RuntimeException {
    public InvalidSignatureValidityException(){super("Invalid Signature Validity Exception");}
    public InvalidSignatureValidityException(String message) {
        super(message);
    }
}
