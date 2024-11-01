package com.inovamed.clinical_study_system.exception;

public class PatientDeletionFailedException extends RuntimeException {
    public PatientDeletionFailedException(String message) {
        super(message);
    }
    public PatientDeletionFailedException(Long id) { super("Failed to delete patient with id: " + id);}
}
