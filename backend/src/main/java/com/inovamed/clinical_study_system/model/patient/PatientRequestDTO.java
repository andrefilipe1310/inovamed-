package com.inovamed.clinical_study_system.model.patient;

import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.model.medical_history.MedicalHistory;
import com.inovamed.clinical_study_system.model.user.UserRoles;

import java.time.LocalDate;

public record PatientRequestDTO (String name, String email, String gender,
                                 LocalDate birth, String phone,
                                 String password, UserRoles roles, MedicalHistory medicalHistory,
                                 DigitalSignature signature,
                                 String doctorKey){
}
