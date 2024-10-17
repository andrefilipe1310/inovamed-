package com.inovamed.clinical_study_system.model.clinical_study_representative;


import com.inovamed.clinical_study_system.model.doctor.DoctorRequestDTO;
import com.inovamed.clinical_study_system.model.doctor.DoctorResponseDTO;
import com.inovamed.clinical_study_system.model.patient.PatientResponseDTO;
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

    public ClinicalStudyRepresentative(ClinicalStudyRepresentativeRequestDTO clinicalRequestDTO) {this.update(clinicalRequestDTO);
    }


    public ClinicalStudyRepresentativeResponseDTO toResponseDTO() {
        return new ClinicalStudyRepresentativeResponseDTO(id, name, email, phone, clinicalRole, experiences);
    }

    public void update(ClinicalStudyRepresentativeRequestDTO requestDTO) {
        this.name = requestDTO.name();
        this.email = requestDTO.email();
        this.clinicalRole = requestDTO.clinicalRole();
        this.phone = requestDTO.phone();
        this.experiences = requestDTO.experience();
        this.password = requestDTO.password();
    }
    public ClinicalStudyRepresentativeResponseDTO toDTO(){
        return new ClinicalStudyRepresentativeResponseDTO(
                this.id,
                this.name,
                this.email,
                this.phone,
                this.clinicalRole,
                this.experiences
        );
    }
}
