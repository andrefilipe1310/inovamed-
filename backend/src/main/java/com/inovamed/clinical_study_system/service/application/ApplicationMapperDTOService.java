package com.inovamed.clinical_study_system.service.application;

import com.inovamed.clinical_study_system.exception.DoctorNotFoundException;
import com.inovamed.clinical_study_system.exception.PatientNotFoundException;
import com.inovamed.clinical_study_system.exception.ResearchNotFoundException;
import com.inovamed.clinical_study_system.model.application.Application;
import com.inovamed.clinical_study_system.model.application.ApplicationRequestDTO;
import com.inovamed.clinical_study_system.model.application.ApplicationResponseDTO;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.patient.Patient;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.repository.ApplicationRepository;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import com.inovamed.clinical_study_system.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationMapperDTOService {
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    ResearchRepository researchRepository;

    public Application toEntity(ApplicationRequestDTO applicationRequestDTO, Long userId){

        Doctor doctor = doctorRepository.findById(userId).orElseThrow(
                ()->{
                    return new DoctorNotFoundException();
                }
        );
        Patient patient = patientRepository.findById(applicationRequestDTO.patientCode()).orElseThrow(
                ()->{
                    return new PatientNotFoundException();
                }
        );
        Research research = researchRepository.findByCode(applicationRequestDTO.researchCode()).orElseThrow(
                ()->{
                    return new ResearchNotFoundException();
                }
        );



        Application application = new Application();
        application.setDoctor(doctor);
        application.setPatient(patient);
        application.setResearch(research);
        application.setMessage(applicationRequestDTO.message());
        application.setStatusApplication(applicationRequestDTO.statusApplication());

        return application;
    }

    public ApplicationResponseDTO toDTO(Application application){
        return new ApplicationResponseDTO(
                application.getPatient().getId(),
                application.getDoctor().getId(),
                application.getResearch().getId(),
                application.getMessage(),
                application.getStatusApplication()
        );
    }
}
