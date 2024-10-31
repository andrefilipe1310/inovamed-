package com.inovamed.clinical_study_system.exception;

public class NotificationDeletionFailedException extends RuntimeException {
    public NotificationDeletionFailedException(String message) {super(message);}
    public NotificationDeletionFailedException() { super("Notification Not Deleted");}
}
