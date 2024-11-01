package com.inovamed.clinical_study_system.exception;

public class DoctorDeletionFailedException extends RuntimeException {
    public DoctorDeletionFailedException(String message) {
        super(message);
    }
  public DoctorDeletionFailedException(Long id) { super("Failed to delete doctor with id: " + id);}
}
