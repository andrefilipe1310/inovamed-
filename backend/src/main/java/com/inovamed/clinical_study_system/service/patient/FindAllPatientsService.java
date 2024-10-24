package com.inovamed.clinical_study_system.service.patient;

import com.inovamed.clinical_study_system.model.patient.PatientResponseDTO;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllPatientsService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientDTOMapperService patientDTOMapperService;

    public List<PatientResponseDTO> execute(){
        return patientRepository.findAll().stream()
                .map(patient -> {
                   return patientDTOMapperService.toDTO(patient);
                })
                .collect(Collectors.toList());
    }
}
