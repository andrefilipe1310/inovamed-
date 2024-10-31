package com.inovamed.clinical_study_system.model.application;

public record ApplicationRequestDTO( Long patientCode, Long doctorCode,Long researchCode, String message,
                                     StatusApplication statusApplication) {
}
