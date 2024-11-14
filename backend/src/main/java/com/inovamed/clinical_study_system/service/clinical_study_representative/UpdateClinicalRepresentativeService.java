package com.inovamed.clinical_study_system.service.clinical_study_representative;

import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.exception.EmailAlreadyRegisteredException;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeUpdateDTO;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateClinicalRepresentativeService {
    @Autowired
    private ClinicalStudyRepresentiveRepository clinicalRepository;
    @Autowired
    private ClinicalRepresentativeDTOMapperService clinicalRepresentativeDTOMapperService;

    public ClinicalStudyRepresentativeResponseDTO execute(Long id, ClinicalStudyRepresentativeUpdateDTO clinicalStudyRepresentativeUpdateDTO) {
        ClinicalStudyRepresentative updatedClinical = clinicalRepository.findById(id).orElseThrow(()->{
            return new ClinicalRepresentativeNotFoundException();
        });
        if (this.clinicalRepository.findByEmail(updatedClinical.getEmail()) != null){
            throw new EmailAlreadyRegisteredException();
        }
        updatedClinical.update(clinicalStudyRepresentativeUpdateDTO);
        return clinicalRepresentativeDTOMapperService.toDTO(this.clinicalRepository.save(updatedClinical));

}}
