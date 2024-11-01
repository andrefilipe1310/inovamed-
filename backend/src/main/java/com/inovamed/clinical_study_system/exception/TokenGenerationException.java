package com.inovamed.clinical_study_system.exception;

public class TokenGenerationException extends RuntimeException {
    public TokenGenerationException(String message) {
        super(message);
    }
  public TokenGenerationException(Exception exception) { super("Error while generated token " + exception);}
}
