package com.inovamed.clinical_study_system.service.research;

import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.research.ResearchRequestDTO;
import com.inovamed.clinical_study_system.model.research.ResearchResponseDTO;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResearchDTOMapperService {

    @Autowired
    private ClinicalStudyRepresentiveRepository clinicalRepresentiveRepository;
    public ResearchResponseDTO toDTO(Research research){
        return new ResearchResponseDTO(
                research.getCode(),
                research.getTitle(),
                research.getArea(),
                research.getNumberOfPatients(),
                research.getAvailableVacancies(),
                research.getResponsibleDoctors(),
                research.getInstitutions(),
                research.getDescription(),
                research.getCriteria(),
                research.getStudyDuration(),
                research.getPhases(),
                research.getCurrentPhase(),
                research.getLocation(),
                research.getAttachments(),
                research.getPatients(),
                research.getClinicalRepresentative()

                );
    }

    public Research toEntity(ResearchRequestDTO researchRequestDTO){

        ClinicalStudyRepresentative clinicalRepresentative = clinicalRepresentiveRepository
                .findById(researchRequestDTO.StudyRepresentativeId())
                .orElseThrow(()->{ return  new RuntimeException("Clinical Representative not found.");});
        Research research = new Research();

        research.setTitle(researchRequestDTO.title());
        research.setArea(researchRequestDTO.area());
        research.setNumberOfPatients(researchRequestDTO.numberOfPatients());
        research.setAvailableVacancies(researchRequestDTO.availableVacancies());
        research.setResponsibleDoctors(researchRequestDTO.responsibleDoctors());
        research.setInstitutions(researchRequestDTO.institutions());
        research.setDescription(researchRequestDTO.description());
        research.setCriteria(researchRequestDTO.criteria());
        research.setStudyDuration(researchRequestDTO.studyDuration());
        research.setPhases(researchRequestDTO.phases());
        research.setCurrentPhase(researchRequestDTO.currentPhase());
        research.setLocation(researchRequestDTO.location());
        research.setAttachments(researchRequestDTO.attachments());
        research.setPatients(List.of());
        research.setClinicalRepresentative(clinicalRepresentative);

        return research;
    }
}
