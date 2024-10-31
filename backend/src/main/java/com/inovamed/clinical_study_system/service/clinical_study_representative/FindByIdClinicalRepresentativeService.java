package com.inovamed.clinical_study_system.service.clinical_study_representative;

import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FindByIdClinicalRepresentativeService {
    @Autowired
    private ClinicalStudyRepresentiveRepository clinicalRepository;
    @Autowired
    private ClinicalRepresentativeDTOMapperService clinicalRepresentativeDTOMapperService;

    public ClinicalStudyRepresentativeResponseDTO execute(Long id) {
        return clinicalRepresentativeDTOMapperService.toDTO(this.clinicalRepository.findById(id).orElseThrow(
                () -> {
                    return new ClinicalRepresentativeNotFoundException();
                }
        ));
    }
}

