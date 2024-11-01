package com.inovamed.clinical_study_system.exception;

public class ClinicalRepresentativeDeletionFailedException extends RuntimeException {
    public ClinicalRepresentativeDeletionFailedException(String message) {
        super(message);
    }
  public ClinicalRepresentativeDeletionFailedException(Long id) { super("Failed to delete Clinical Representative with id: " + id); }
}
