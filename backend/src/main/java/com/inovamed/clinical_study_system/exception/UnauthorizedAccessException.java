package com.inovamed.clinical_study_system.exception;

public class UnauthorizedAccessException extends RuntimeException {
  public  UnauthorizedAccessException(){super("Unauthorized Access Exception");}
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
