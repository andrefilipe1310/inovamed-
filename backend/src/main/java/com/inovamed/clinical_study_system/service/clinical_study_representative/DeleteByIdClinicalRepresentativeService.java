package com.inovamed.clinical_study_system.service.clinical_study_representative;

import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteByIdClinicalRepresentativeService {
    @Autowired
    private ClinicalStudyRepresentiveRepository clinicalRepository;

    public void execute(Long id) {
        if (!clinicalRepository.existsById(id)) {
            throw new ClinicalRepresentativeNotFoundException("Clinical representative with ID " + id + " not found.");
        }

        clinicalRepository.deleteById(id);
    }
}
