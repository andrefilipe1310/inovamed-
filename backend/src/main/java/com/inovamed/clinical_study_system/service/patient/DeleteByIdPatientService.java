package com.inovamed.clinical_study_system.service.patient;

import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteByIdPatientService {
    @Autowired
    private PatientRepository patientRepository;

    public void execute(Long id){
        patientRepository.deleteById(id);

        if (patientRepository.existsById(id)){
            throw  new RuntimeException("Filed deleted.");
        }
    }
}
