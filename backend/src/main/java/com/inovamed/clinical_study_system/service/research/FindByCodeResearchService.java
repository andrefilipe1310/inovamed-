package com.inovamed.clinical_study_system.service.research;

import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.model.research.ResearchResponseDTO;
import com.inovamed.clinical_study_system.repository.ResearchRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindByCodeResearchService {
    @Autowired
    ResearchRepository researchRepository;
    @Autowired
    ResearchDTOMapperService researchDTOMapperService;
    @Transactional
    public ResearchResponseDTO execute(int code){
        return researchDTOMapperService.toDTO(researchRepository.findByCode(code).orElseThrow(()->{
                    return new ClinicalRepresentativeNotFoundException();
                }),true);
    }
}
