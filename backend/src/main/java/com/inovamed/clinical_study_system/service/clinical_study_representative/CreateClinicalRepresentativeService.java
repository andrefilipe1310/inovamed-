package com.inovamed.clinical_study_system.service.clinical_study_representative;

import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeRequestDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.model.user.RegisterDTO;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateClinicalRepresentativeService {
    @Autowired
    private ClinicalStudyRepresentiveRepository clinicalRepository;
    @Autowired
    private ClinicalRepresentativeDTOMapperService clinicalRepresentativeDTOMapperService;

    public  ClinicalStudyRepresentativeResponseDTO execute(ClinicalStudyRepresentativeRequestDTO clincalRequestDTO){
        ClinicalStudyRepresentative clinicalRepresentative = clinicalRepresentativeDTOMapperService.toEntity(clincalRequestDTO);
        return clinicalRepresentativeDTOMapperService.toDTO(clinicalRepository.save(clinicalRepresentative));
    }
}
