package com.inovamed.clinical_study_system.model.clinical_study_representative;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_clinical_study_representative")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalStudyRepresentative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String clinicalRole;
    private String experiences;
    private String password;

    public ClinicalStudyRepresentative(ClinicalStudyRepresentativeRequestDTO requestDTO){
        this.name = requestDTO.name();
    }

    public ClinicalStudyRepresentativeResponseDTO toDTO(){
        return new ClinicalStudyRepresentativeResponseDTO(

        );
    }
}
