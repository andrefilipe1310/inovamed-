package com.inovamed.clinical_study_system.model.notification;

import com.inovamed.clinical_study_system.model.attachment.Attachment;

import java.util.List;

public record NotificationResquestDTO(
         String sender
        ,Long id
        ,String title
        ,String message
        ,List<Attachment> attachment
        ,List<Long> doctorsId
        ,List<Long> patientsId
){ }
