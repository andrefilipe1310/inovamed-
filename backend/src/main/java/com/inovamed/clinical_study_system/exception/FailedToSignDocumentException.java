package com.inovamed.clinical_study_system.exception;

import java.security.GeneralSecurityException;

public class FailedToSignDocumentException extends RuntimeException {
    public FailedToSignDocumentException(){super("Failed to sign document");}
    public FailedToSignDocumentException(String message) {
        super(message);
    }
}
