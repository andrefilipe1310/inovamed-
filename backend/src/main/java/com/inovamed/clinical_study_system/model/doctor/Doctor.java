package com.inovamed.clinical_study_system.model.doctor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String password;
    private String specialty;
    private String Crm;

    public Doctor(DoctorRequestDTO doctorRequestDTO) {
       this.update(doctorRequestDTO);
    }

    public DoctorResponseDTO toResponseDTO() {
        return new DoctorResponseDTO(id, name, email, specialty, Crm);
    }



    public void update(DoctorRequestDTO doctorRequestDTO) {
        this.name = doctorRequestDTO.name();
        this.email = doctorRequestDTO.email();
        this.password = doctorRequestDTO.password();
        this.specialty = doctorRequestDTO.specialty();
        this.Crm = doctorRequestDTO.Crm();
    }

    public void registerPatient() {

    }



    public void approvePatient() {

    }



}


