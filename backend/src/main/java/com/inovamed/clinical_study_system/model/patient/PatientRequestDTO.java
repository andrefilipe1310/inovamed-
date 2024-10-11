package com.inovamed.clinical_study_system.model.patient;

import com.inovamed.clinical_study_system.model.doctor.Doctor;

public record PatientRequestDTO (String name, Long doctorId, String form){
}
