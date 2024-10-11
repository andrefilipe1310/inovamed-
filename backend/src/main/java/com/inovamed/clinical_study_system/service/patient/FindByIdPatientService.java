package com.inovamed.clinical_study_system.service.patient;


import com.inovamed.clinical_study_system.model.patient.PatientResponseDTO;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindByIdPatientService {
    @Autowired
    private PatientRepository patientRepository;


    public PatientResponseDTO execute(Long id){
        return this.patientRepository.findById(id).orElseThrow(
                () -> {
                    return new RuntimeException("Patient not found.");
                }
        ).toDTO();
    }
}
