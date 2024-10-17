package com.inovamed.clinical_study_system.service.clinical_study_representative;


import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeRequestDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ClinicalRepresentativeDTOMapperService {

    public ClinicalStudyRepresentativeResponseDTO toDTO(ClinicalStudyRepresentative clinicalRepresentative){

        return new ClinicalStudyRepresentativeResponseDTO(
                clinicalRepresentative.getId(),
                clinicalRepresentative.getName(),
                clinicalRepresentative.getEmail(),
                clinicalRepresentative.getPhone(),
                clinicalRepresentative.getClinicalRole(),
                clinicalRepresentative.getExperiences()
        );
    }
    public ClinicalStudyRepresentative toEntity(ClinicalStudyRepresentativeRequestDTO requestDTO){
        ClinicalStudyRepresentative clinicalRepresentative = new ClinicalStudyRepresentative();
        clinicalRepresentative.setName(requestDTO.name());
        clinicalRepresentative.setEmail(requestDTO.email());
        clinicalRepresentative.setClinicalRole(requestDTO.clinicalRole());
        clinicalRepresentative.setPhone(requestDTO.phone());
        clinicalRepresentative.setExperiences(requestDTO.experience());
        clinicalRepresentative.setPassword(requestDTO.password());

        return clinicalRepresentative;
    }
}
