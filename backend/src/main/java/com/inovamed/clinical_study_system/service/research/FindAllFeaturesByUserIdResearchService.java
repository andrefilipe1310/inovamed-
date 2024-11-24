package com.inovamed.clinical_study_system.service.research;


import com.inovamed.clinical_study_system.model.research.ResearchFeaturesResponseDTO;

import com.inovamed.clinical_study_system.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllFeaturesByUserIdResearchService {
    @Autowired
    private ResearchRepository researchRepository;
 
    public List<ResearchFeaturesResponseDTO> execute(Long userId){

        return this.researchRepository.findAllByClinicalRepresentativeId(userId).stream().map(research -> {
            return new ResearchFeaturesResponseDTO(research.getTitle(),research.getCode());
        }).collect(Collectors.toList());
    }
}
