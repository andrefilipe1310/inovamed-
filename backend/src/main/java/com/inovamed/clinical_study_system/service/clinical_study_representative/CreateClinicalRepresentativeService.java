package com.inovamed.clinical_study_system.service.clinical_study_representative;

import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeRequestDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateClinicalRepresentativeService {
    @Autowired
    private ClinicalStudyRepresentiveRepository repository;

    public ClinicalStudyRepresentativeResponseDTO execute(ClinicalStudyRepresentativeRequestDTO clincalRequestDTO){
        ClinicalStudyRepresentative clinical = new ClinicalStudyRepresentative(clincalRequestDTO);

        return repository.save(clinical).toDTO();
    }
}
