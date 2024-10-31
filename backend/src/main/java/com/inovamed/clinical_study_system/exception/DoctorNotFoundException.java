package com.inovamed.clinical_study_system.exception;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(String message) {
        super(message);
    }
    public DoctorNotFoundException() {
        super("Doctor not found.");
    }
}
