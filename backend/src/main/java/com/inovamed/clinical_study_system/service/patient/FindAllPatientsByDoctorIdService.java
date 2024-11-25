package com.inovamed.clinical_study_system.service.patient;

import com.inovamed.clinical_study_system.exception.DoctorNotFoundException;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.patient.PatientResponseDTO;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllPatientsByDoctorIdService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientDTOMapperService patientDTOMapperService;
    public List<PatientResponseDTO> execute(Long userId){
        Doctor doctor = doctorRepository.findById(userId)
                .orElseThrow(()->{
                    return new DoctorNotFoundException();
                });

        return doctor.getPatients()
                .stream().map(patient -> {
                    return patientDTOMapperService.toDTO(patient);
                })
                .collect(Collectors.toList());
    }
}
