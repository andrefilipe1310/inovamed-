package com.inovamed.clinical_study_system.model.doctor;

public enum DoctorExperienceEnum {
    NEVER("never"),
    LIMITED_EXPERIENCE("limited_experience"),
    EXPERIENCE("experience");

    private String experience;

    DoctorExperienceEnum(String experience) {
        this.experience = experience;
    }

    public String getExperience() {
        return this.experience;
    }
}
