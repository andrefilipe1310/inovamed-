package com.inovamed.clinical_study_system.model.patient;

import com.inovamed.clinical_study_system.model.medical_history.MedicalHistory;

import java.time.LocalDate;

public record PatientRequestDTO (String name, String email, String gender,
                                 LocalDate birth, String phone,
                                 String password, MedicalHistory medicalHistory,
                                 String signature,
                                 String doctorKey){
}
