package com.inovamed.clinical_study_system.model.notification;

import com.inovamed.clinical_study_system.model.attachment.Attachment;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.patient.Patient;

import java.util.List;

public record NotificationResponseDTO(Long id
        , String title
        , String message
        , List<Attachment> attachment
        ,List<Doctor> doctors
        ,List<Patient> patients) {
}
