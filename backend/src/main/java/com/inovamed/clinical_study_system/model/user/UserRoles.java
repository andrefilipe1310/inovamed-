package com.inovamed.clinical_study_system.model.user;

public enum UserRoles {
    DOCTOR("doctor"),
    PATIENT("patient"),
    STUDYREPRESENTATIVE("studyRepresentative");

    private String role;

    UserRoles(String role){
        this.role = role;
    }
    public String getRole(){
        return this.role;
    }

}
