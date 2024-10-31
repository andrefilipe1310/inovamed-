package com.inovamed.clinical_study_system.exception;

public class AttachmentNotFoundException extends RuntimeException {
    public AttachmentNotFoundException(String message) { super(message);}
    public AttachmentNotFoundException() {super("Attachment not found.");}
}
