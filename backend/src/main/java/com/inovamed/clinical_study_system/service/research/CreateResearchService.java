package com.inovamed.clinical_study_system.service.research;


import com.inovamed.clinical_study_system.model.research.Phases;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.research.ResearchRequestDTO;
import com.inovamed.clinical_study_system.model.research.ResearchResponseDTO;
import com.inovamed.clinical_study_system.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class CreateResearchService {
    @Autowired
    private ResearchRepository researchRepository;
    @Autowired
    private ResearchDTOMapperService researchDTOMapperService;

    public ResearchResponseDTO execute(ResearchRequestDTO researchRequestDTO, List<MultipartFile> files, Long userId) throws IOException {
        Research research = researchDTOMapperService.toEntity(researchRequestDTO, files, userId);
        return researchDTOMapperService.toDTO(this.researchRepository.save(research),false);
    }

}
