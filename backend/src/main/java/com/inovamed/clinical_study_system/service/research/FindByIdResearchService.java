package com.inovamed.clinical_study_system.service.research;

import com.inovamed.clinical_study_system.model.research.ResearchResponseDTO;
import com.inovamed.clinical_study_system.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindByIdResearchService {
    @Autowired
    private ResearchRepository researchRepository;
    @Autowired
    private ResearchDTOMapperService researchDTOMapperService;

    public ResearchResponseDTO execute(Long id){
        return researchDTOMapperService.toDTO(this.researchRepository.findById(id)
                .orElseThrow(()->{
                    return new RuntimeException("Research not found.");
                }));
    }
}
