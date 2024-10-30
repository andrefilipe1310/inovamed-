package com.inovamed.clinical_study_system.service.clinical_study_representative;


import com.inovamed.clinical_study_system.exception.UserAlreadyExistsException;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeRequestDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.model.user.RegisterDTO;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClinicalRepresentativeDTOMapperService {
    @Autowired
    private ClinicalStudyRepresentiveRepository ClinicalRepresentativeRepository;

    public ClinicalStudyRepresentativeResponseDTO toDTO(ClinicalStudyRepresentative clinicalRepresentative){

        return new ClinicalStudyRepresentativeResponseDTO(
                clinicalRepresentative.getId(),
                clinicalRepresentative.getName(),
                clinicalRepresentative.getEmail(),
                clinicalRepresentative.getPassword(),
                clinicalRepresentative.getRoles(),
                clinicalRepresentative.getPhone(),
                clinicalRepresentative.getClinicalRole(),
                clinicalRepresentative.getExperiences()
        );
    }
    public ClinicalStudyRepresentative toEntity(ClinicalStudyRepresentativeRequestDTO requestDTO){
        if(ClinicalRepresentativeRepository.findByEmail(requestDTO.email()) != null){
            throw new UserAlreadyExistsException();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(requestDTO.password());
        ClinicalStudyRepresentative clinicalRepresentative = new ClinicalStudyRepresentative();
        clinicalRepresentative.setName(requestDTO.name());
        clinicalRepresentative.setEmail(requestDTO.email());
        clinicalRepresentative.setRoles(requestDTO.roles());
        clinicalRepresentative.setClinicalRole(requestDTO.clinicalRole());
        clinicalRepresentative.setPhone(requestDTO.phone());
        clinicalRepresentative.setExperiences(requestDTO.experience());
        clinicalRepresentative.setPassword(encryptedPassword);



        return clinicalRepresentative;
    }
}
