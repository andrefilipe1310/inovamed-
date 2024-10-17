package com.inovamed.clinical_study_system.model.doctor;

public record DoctorRequestDTO(String name, String email, DoctorExperienceEnum doctorExperienceEnum, String clinic
        , String phone, String password, String specialty, String Crm) {

}
