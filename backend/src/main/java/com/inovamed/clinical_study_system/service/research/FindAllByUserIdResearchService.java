package com.inovamed.clinical_study_system.service.research;



import com.inovamed.clinical_study_system.model.research.ResearchResponseDTO;
import com.inovamed.clinical_study_system.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllByUserIdResearchService {

    @Autowired
    private ResearchRepository researchRepository;
    @Autowired
    ResearchDTOMapperService researchDTOMapperService;

    public List<ResearchResponseDTO> execute(Long userId){

        return researchRepository.findAllByClinicalRepresentativeId(userId).stream().map(
                research -> {
                    return researchDTOMapperService.toDTO(research,false);
                }
        ).collect(Collectors.toList());

    }
}
