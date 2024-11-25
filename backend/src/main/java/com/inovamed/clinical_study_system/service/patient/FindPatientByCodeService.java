package com.inovamed.clinical_study_system.service.patient;

import com.inovamed.clinical_study_system.exception.PatientNotFoundException;
import com.inovamed.clinical_study_system.model.patient.PatientResponseDTO;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindPatientByCodeService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private
    PatientDTOMapperService patientDTOMapperService;
    public PatientResponseDTO execute(String code){
        return patientDTOMapperService.toDTO(patientRepository.findByCode(code).orElseThrow(
                ()->{
                    return new PatientNotFoundException();
                }
        ));

    }
}
