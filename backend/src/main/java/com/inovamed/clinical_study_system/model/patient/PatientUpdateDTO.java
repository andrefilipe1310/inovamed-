package com.inovamed.clinical_study_system.model.patient;

import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.model.medical_history.MedicalHistory;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.research.Research;

import java.time.LocalDate;
import java.util.List;

public record PatientUpdateDTO(
            String name,
            String email,
            String gender,
            LocalDate birth,
            String phone,
            String password,
            Boolean digitalSignatureConsent,
            Boolean responsibleDoctor,
            List<String> authorizations,
            List<Research> researches,
            List<Notification> notifications,
            MedicalHistory medicalHistory,
            DigitalSignature signature
    ) {
}
