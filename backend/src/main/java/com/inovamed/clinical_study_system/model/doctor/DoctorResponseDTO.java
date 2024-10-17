package com.inovamed.clinical_study_system.model.doctor;

import com.inovamed.clinical_study_system.model.application.Application;
import com.inovamed.clinical_study_system.model.notification.Notification;

import java.util.List;

public record DoctorResponseDTO(Long id, String name, String email, DoctorExperienceEnum doctorExperienceEnum,
                                String clinic, String phone, String specialty, String Crm,
                                List<Application> applicationsSubmitted, List<Notification> notifications,
                                List<String> patientsNames) {
}
