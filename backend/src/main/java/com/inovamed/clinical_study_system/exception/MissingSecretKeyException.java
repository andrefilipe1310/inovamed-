package com.inovamed.clinical_study_system.exception;

public class MissingSecretKeyException extends RuntimeException {
  public MissingSecretKeyException(){super("Secret key is missing for token generation.");}
    public MissingSecretKeyException(String message) {
        super(message);
    }
}
