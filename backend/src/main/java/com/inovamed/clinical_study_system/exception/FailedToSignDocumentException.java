package com.inovamed.clinical_study_system.exception;



public class FailedToSignDocumentException extends RuntimeException {
    public FailedToSignDocumentException(){super("Failed to sign document");}
    public FailedToSignDocumentException(String message) {
        super(message);
    }
}
