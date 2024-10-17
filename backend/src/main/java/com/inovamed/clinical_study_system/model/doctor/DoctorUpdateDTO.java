package com.inovamed.clinical_study_system.model.doctor;

public record DoctorUpdateDTO(
        String name,
        String email,
        String clinic,
        String specialty,
        DoctorExperienceEnum doctorExperienceEnum,
        String crm,
        String phone,
        String password
) {
}
