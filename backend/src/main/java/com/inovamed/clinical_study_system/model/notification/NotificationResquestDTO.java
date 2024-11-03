package com.inovamed.clinical_study_system.model.notification;

import com.inovamed.clinical_study_system.model.attachment.Attachment;

import java.util.List;

public record NotificationResquestDTO(
         Long sender
        ,String title
        ,String message

        ,List<Long> doctorsId
        ,List<Long> patientsId
){ }
