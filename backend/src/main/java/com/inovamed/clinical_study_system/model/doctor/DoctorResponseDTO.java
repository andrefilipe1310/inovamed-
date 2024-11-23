package com.inovamed.clinical_study_system.model.doctor;

import com.inovamed.clinical_study_system.model.application.Application;
import com.inovamed.clinical_study_system.model.application.ApplicationResponseDTO;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.notification.NotificationResponseDTO;
import com.inovamed.clinical_study_system.model.user.UserRoles;

import java.util.List;

public record DoctorResponseDTO(Long id, String key, String name, String email, String password, UserRoles roles, DoctorExperienceEnum doctorExperienceEnum,
                                String clinic, String phone, String specialty, String Crm,
                                List<ApplicationResponseDTO> applicationsSubmitted, List<NotificationResponseDTO> notifications,
                                List<String> patientsNames) {
}

