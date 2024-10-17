package com.inovamed.clinical_study_system.model.doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private DoctorExperienceEnum doctorExperienceEnum;
    private String clinic;
    private String contactNumber;
    private String password;
    private String specialty;
    private String Crm;
    @Column(unique = true)
    private String key;

    public Doctor(DoctorRequestDTO doctorRequestDTO) {
        this.update(doctorRequestDTO);
    }

    public DoctorResponseDTO toResponseDTO() {
        return new DoctorResponseDTO(id, name, email, doctorExperienceEnum, clinic, contactNumber, specialty, Crm);
    }

    public void update(DoctorRequestDTO doctorRequestDTO) {
        this.name = doctorRequestDTO.name();
        this.email = doctorRequestDTO.email();
        this.doctorExperienceEnum = doctorRequestDTO.doctorExperienceEnum();
        this.clinic = doctorRequestDTO.clinic();
        this.contactNumber = doctorRequestDTO.contactNumber();
        this.password = doctorRequestDTO.password();
        this.specialty = doctorRequestDTO.specialty();
        this.Crm = doctorRequestDTO.Crm();
    }

    public void registerPatient() {
    }

    public void approvePatient() {
    }



}


