package com.inovamed.clinical_study_system.model.application;

public record ApplicationResponseDTO(Long patientCode, Long doctorCode,Long researchCode, String message,
                                     StatusApplication statusApplication) {
}
