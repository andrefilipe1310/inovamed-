package com.inovamed.clinical_study_system.model.patient;

import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.notification.Notification;

import java.util.List;

public record PatientResponseDTO(String name, boolean digitalSignatureConsent,
                                 boolean responsibleDoctor, List<String> authorizations,
                                 List<Research> researches,
                                 List<Notification> notifications,
                                 String doctorName, String doctorCRM) {
}
