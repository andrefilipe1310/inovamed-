package com.inovamed.clinical_study_system.exception;

public class ResearchDeletionFailedException extends RuntimeException {
  public ResearchDeletionFailedException(String message) {
    super(message);
  }
  public ResearchDeletionFailedException(Long id) { super("Failed to delete Research with id: " + id);}
}
