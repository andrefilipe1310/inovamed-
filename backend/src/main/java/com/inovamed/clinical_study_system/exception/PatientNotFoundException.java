package com.inovamed.clinical_study_system.exception;

public class PatientNotFoundException extends RuntimeException {
  public PatientNotFoundException() {
    super("Patient not found.");
  }
  public PatientNotFoundException(String message){
    super(message);
  }
}
