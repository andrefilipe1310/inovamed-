package com.inovamed.clinical_study_system.service.patient;


import com.inovamed.clinical_study_system.model.patient.Patient;
import com.inovamed.clinical_study_system.model.patient.PatientRequestDTO;
import com.inovamed.clinical_study_system.model.patient.PatientResponseDTO;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePatientService {
    @Autowired
    private PatientRepository patientRepository;


    public PatientResponseDTO execute(PatientRequestDTO patientRequestDTO){
        return patientRepository.save(new Patient(patientRequestDTO)).toDTO();

    };
}
