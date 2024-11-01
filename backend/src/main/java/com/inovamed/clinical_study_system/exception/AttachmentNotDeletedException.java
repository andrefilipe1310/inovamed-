package com.inovamed.clinical_study_system.exception;

public class AttachmentNotDeletedException extends RuntimeException {
    public AttachmentNotDeletedException(String message) {
        super(message);
    }
  public AttachmentNotDeletedException() { super("Attachment not deleted."); }
}
