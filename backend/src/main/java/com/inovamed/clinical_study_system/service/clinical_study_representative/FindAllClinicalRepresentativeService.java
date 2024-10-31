package com.inovamed.clinical_study_system.service.clinical_study_representative;

import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllClinicalRepresentativeService {
    @Autowired
    private ClinicalStudyRepresentiveRepository repository;
    @Autowired
    private ClinicalRepresentativeDTOMapperService clinicalRepresentativeDTOMapperService;

    public List<ClinicalStudyRepresentativeResponseDTO> execute(){
        return repository.findAll().stream().map((clinical)->{
            return clinicalRepresentativeDTOMapperService.toDTO(clinical);
        }).collect(Collectors.toList());
    }
}
