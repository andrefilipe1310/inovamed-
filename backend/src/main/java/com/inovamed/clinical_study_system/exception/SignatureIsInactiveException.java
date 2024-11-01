package com.inovamed.clinical_study_system.exception;

public class SignatureIsInactiveException extends RuntimeException {
    public SignatureIsInactiveException(String message) {
        super(message);
    }
  public SignatureIsInactiveException() { super("Signature is already inactive.");}
}
