package com.inovamed.clinical_study_system.service.research;


import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.research.ResearchResponseDTO;
import com.inovamed.clinical_study_system.model.research.ResearchUpdateDTO;
import com.inovamed.clinical_study_system.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateResearchService {
    @Autowired
    private ResearchRepository researchRepository;
    @Autowired
    private ResearchDTOMapperService researchDTOMapperService;

    public ResearchResponseDTO execute(Long id,ResearchUpdateDTO researchUpdateDTO){
        Research updatedResearch = this.researchRepository.findById(id).orElseThrow(()->{
            return new RuntimeException("Research not found.");
        });
        updatedResearch.update(researchUpdateDTO);
        return researchDTOMapperService.toDTO(this.researchRepository.save(updatedResearch));
    }
}
