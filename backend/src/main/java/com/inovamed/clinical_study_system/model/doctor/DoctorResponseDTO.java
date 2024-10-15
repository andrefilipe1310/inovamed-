package com.inovamed.clinical_study_system.model.doctor;

public record DoctorResponseDTO(Long id, String name, String email, DoctorExperienceEnum doctorExperienceEnum, String clinic, String contactNumber, String specialty, String Crm) {
}
