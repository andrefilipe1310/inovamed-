package com.inovamed.clinical_study_system.model.doctor;

public record DoctorRequestDTO(Long id, String name, String email, DoctorExperienceEnum doctorExperienceEnum, String clinic, String contactNumber, String password, String specialty, String Crm) {

}
