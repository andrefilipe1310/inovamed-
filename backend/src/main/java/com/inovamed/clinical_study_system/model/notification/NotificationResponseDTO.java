package com.inovamed.clinical_study_system.model.notification;

import com.inovamed.clinical_study_system.model.attachment.Attachment;


import java.util.List;

public record NotificationResponseDTO(
        Long id
        , String title
        , String message
        , List<Attachment> attachment
        , String sender
        ,List<String> doctorsConfirmation
        ,List<String> patientsConfirmation) {
}
