package com.inovamed.clinical_study_system.exception;

public class FailedToGenerateKeyPairException extends RuntimeException {
    public FailedToGenerateKeyPairException(){super("Failed to generate key pair");}
    public FailedToGenerateKeyPairException(String message) {
        super(message);
    }
}
