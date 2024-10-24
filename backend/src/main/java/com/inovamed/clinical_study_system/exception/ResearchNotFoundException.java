package com.inovamed.clinical_study_system.exception;

public class ResearchNotFoundException extends RuntimeException {
  public ResearchNotFoundException(String message) {
    super(message);
  }
  public ResearchNotFoundException() {
    super("Research not found.");
  }
}
