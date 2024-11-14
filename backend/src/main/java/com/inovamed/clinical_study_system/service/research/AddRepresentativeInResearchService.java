package com.inovamed.clinical_study_system.service.research;

import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.exception.ResearchNotFoundException;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.research.ResearchAddRepresentativeDTO;
import com.inovamed.clinical_study_system.model.research.ResearchResponseDTO;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import com.inovamed.clinical_study_system.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddRepresentativeInResearchService {
    @Autowired
    private ResearchRepository researchRepository;
    @Autowired
    private ClinicalStudyRepresentiveRepository clinicalRepresentiveRepository;
    @Autowired
    private ResearchDTOMapperService researchDTOMapperService;

    public ResearchResponseDTO execute(ResearchAddRepresentativeDTO researchAddRepresentativeDTO) {
        ClinicalStudyRepresentative clinicalRepresentative = clinicalRepresentiveRepository
                .findById(researchAddRepresentativeDTO
                        .clinicalRepresentativeId()).orElseThrow(() -> {
                    throw new ClinicalRepresentativeNotFoundException();
                });

         clinicalRepresentiveRepository
                .findById(researchAddRepresentativeDTO
                        .clinicalRepresentativeIdCurrent()).orElseThrow(() -> {
                    throw new ClinicalRepresentativeNotFoundException();
                });

        Research research = researchRepository.findById(researchAddRepresentativeDTO
                        .researchId())
                .orElseThrow(() -> {
                    throw new ResearchNotFoundException();
                });

        research.setClinicalRepresentative(clinicalRepresentative);
        return researchDTOMapperService.toDTO(this.researchRepository.save(research),false);
    }
}
