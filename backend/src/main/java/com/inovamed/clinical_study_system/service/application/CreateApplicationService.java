package com.inovamed.clinical_study_system.service.application;


import com.inovamed.clinical_study_system.exception.DoctorNotFoundException;
import com.inovamed.clinical_study_system.exception.PatientNotFoundException;
import com.inovamed.clinical_study_system.model.application.Application;
import com.inovamed.clinical_study_system.model.application.ApplicationRequestDTO;
import com.inovamed.clinical_study_system.model.application.ApplicationResponseDTO;
import com.inovamed.clinical_study_system.repository.ApplicationRepository;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ApplicationMapperDTOService applicationMapperDTOService;


    public ApplicationResponseDTO execute(ApplicationRequestDTO applicationRequestDTO, Long userId){
        Application application = applicationMapperDTOService.toEntity(applicationRequestDTO, userId);
        return applicationMapperDTOService.toDTO(applicationRepository.save(application));

    }
}
