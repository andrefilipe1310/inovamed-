package com.inovamed.clinical_study_system.service.medical_history;


import com.inovamed.clinical_study_system.exception.DoctorNotFoundException;
import com.inovamed.clinical_study_system.exception.PatientNotFoundException;
import com.inovamed.clinical_study_system.exception.UnauthorizedAccessException;
import com.inovamed.clinical_study_system.model.medical_history.MedicalHistory;
import com.inovamed.clinical_study_system.model.medical_history.MedicalHistoryRequestDTO;
import com.inovamed.clinical_study_system.model.medical_history.MedicalHistoryResponseDTO;
import com.inovamed.clinical_study_system.model.patient.Patient;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import com.inovamed.clinical_study_system.repository.MedicalHistoryRepository;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class CreateMedicalHistoryByPatientIdService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;
    @Autowired
    private MedicalHistoryMapperDTOService medicalHistoryMapperDTOService;

    public MedicalHistoryResponseDTO execute(MedicalHistoryRequestDTO medicalHistoryRequestDTO, Long userId){

        if(!doctorRepository.existsById(userId)){
            throw new DoctorNotFoundException();
        }

        Patient patient = patientRepository.findByCode(medicalHistoryRequestDTO.patientCode())
                .orElseThrow(()->{
                    return new PatientNotFoundException();
                });

        if (!Objects.equals(patient.getDoctor().getId(), userId)){
            throw new UnauthorizedAccessException("Patient does not belong to this doctor.");
        }

        MedicalHistory medicalHistory = medicalHistoryRepository.findByPatientCode(medicalHistoryRequestDTO.patientCode());

        medicalHistory.setText(medicalHistoryRequestDTO.message());

        return  medicalHistoryMapperDTOService.toDTO(medicalHistoryRepository.save(medicalHistory));


    }
}
