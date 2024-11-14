package com.inovamed.clinical_study_system.service.research;


import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.exception.ResearchNotFoundException;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.research.ResearchResponseDTO;
import com.inovamed.clinical_study_system.model.research.ResearchUpdateDTO;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import com.inovamed.clinical_study_system.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateResearchService {
    @Autowired
    private ResearchRepository researchRepository;
    @Autowired
    private ClinicalStudyRepresentiveRepository clinicalRepository;
    @Autowired
    private ResearchDTOMapperService researchDTOMapperService;

    public ResearchResponseDTO execute(Long userId,ResearchUpdateDTO researchUpdateDTO,int code){
         this.clinicalRepository.findById(userId).orElseThrow(()->{
            throw new ClinicalRepresentativeNotFoundException();
        });
        Research updatedResearch = this.researchRepository.findByCode(code).orElseThrow(()->{
            return new ResearchNotFoundException();
        });

        updatedResearch.update(researchUpdateDTO);
        return researchDTOMapperService.toDTO(this.researchRepository.save(updatedResearch),true);
    }
}
