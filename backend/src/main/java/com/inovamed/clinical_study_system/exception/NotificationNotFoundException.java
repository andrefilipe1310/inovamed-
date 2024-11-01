package com.inovamed.clinical_study_system.exception;

public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException(String message) {super(message);}
    public NotificationNotFoundException() { super("Notification Not Found");}
}
