package com.inovamed.clinical_study_system.model.notification;

import com.inovamed.clinical_study_system.model.attachment.Attachment;
import com.inovamed.clinical_study_system.model.attachment.AttachmentFindResponseDTO;


import java.util.List;

public record NotificationResponseDTO(
        Long id
        , String title
        , String message
        , List<AttachmentFindResponseDTO> attachment

        , String sender
        ,List<String> doctorsConfirmation
        ,List<String> patientsConfirmation) {
}
