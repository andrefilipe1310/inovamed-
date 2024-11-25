package com.inovamed.clinical_study_system.model.application;

public record ApplicationPatientResponseDTO(
       String name,
       String code,
       String gender,
       StatusApplication statusApplication
) {
}
