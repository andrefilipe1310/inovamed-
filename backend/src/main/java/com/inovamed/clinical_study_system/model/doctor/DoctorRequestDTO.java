package com.inovamed.clinical_study_system.model.doctor;


import com.inovamed.clinical_study_system.model.user.UserRoles;

public record DoctorRequestDTO(String name, String email, UserRoles roles, DoctorExperienceEnum doctorExperienceEnum, String clinic
        , String phone, String password, String specialty, String Crm) {
}
