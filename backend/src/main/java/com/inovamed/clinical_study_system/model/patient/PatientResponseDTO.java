package com.inovamed.clinical_study_system.model.patient;

import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.user.UserRoles;

import java.time.LocalDate;
import java.util.List;

public record PatientResponseDTO(String name, String email, String password, UserRoles roles, String code,
                                 String gender, LocalDate birth,
                                 Boolean digitalSignatureConsent,
                                 Boolean responsibleDoctor,
                                 List<String> authorizations,
                                 List<Research> researches,
                                 List<Notification> notifications,
                                 DigitalSignature signature, String doctorName, String doctorCRM) {
}
