package com.inovamed.clinical_study_system.service.application;

import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.exception.ResearchNotFoundException;
import com.inovamed.clinical_study_system.model.application.Application;
import com.inovamed.clinical_study_system.model.application.ApplicationPatientRequestDTO;
import com.inovamed.clinical_study_system.model.application.ApplicationPatientResponseDTO;
import com.inovamed.clinical_study_system.model.attachment.AttachmentFindResponseDTO;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.repository.ApplicationRepository;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import com.inovamed.clinical_study_system.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllApplicationsByResearchCodeService {
    @Autowired
    private ClinicalStudyRepresentiveRepository clinicalRepresentiveRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ResearchRepository researchRepository;

    public List<ApplicationPatientResponseDTO> execute(ApplicationPatientRequestDTO applicationPatientRequestDTO, Long userId){
        if (!clinicalRepresentiveRepository.existsById(userId)){
            throw new ClinicalRepresentativeNotFoundException();
        }
        Research research = researchRepository.findByCode(applicationPatientRequestDTO.researchCode())
                .orElseThrow(()->{
                    return new ResearchNotFoundException();
                });

        return research.getApplications().stream().map(
                application ->{
                    return new ApplicationPatientResponseDTO(application.getPatient().getName(),
                            application.getPatient().getCode(),
                            application.getPatient().getGender(),
                            application.getStatusApplication());
                }
        ).collect(Collectors.toList());
    }
}
