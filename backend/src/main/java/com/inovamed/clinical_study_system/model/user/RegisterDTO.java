package com.inovamed.clinical_study_system.model.user;

public record RegisterDTO(String email, String password, UserRoles roles) {
}
