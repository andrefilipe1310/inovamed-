package com.inovamed.clinical_study_system.service.patient;

import com.inovamed.clinical_study_system.exception.PatientNotFoundException;
import com.inovamed.clinical_study_system.model.patient.Patient;
import com.inovamed.clinical_study_system.model.patient.PatientResponseDTO;
import com.inovamed.clinical_study_system.model.patient.PatientUpdateDTO;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientDTOMapperService patientDTOMapperService;

    public PatientResponseDTO execute(Long id, PatientUpdateDTO patientUpdateDTO){
        Patient updatedPatient = patientRepository.findById(id)
                .orElseThrow(()->{
                    return new PatientNotFoundException();
                });
        updatedPatient.update(patientUpdateDTO);
        return patientDTOMapperService.toDTO(this.patientRepository.save(updatedPatient));
    }
}
