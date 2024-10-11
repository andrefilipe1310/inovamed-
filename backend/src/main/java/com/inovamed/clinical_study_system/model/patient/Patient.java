package com.inovamed.clinical_study_system.model.patient;

import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.notification.Notification;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "tb_patient")
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean digitalSignatureConsent = false;
    private boolean responsibleDoctor = true;
    private List<String> authorizations = List.of("Permissão para usar dados","Permissão para notificar");
    @OneToMany
    private List<Research> researches;
    @OneToMany
    private List<Notification> notifications;
    private String form;
    private String signature;
    @OneToOne
    private Doctor doctor;

    public Patient(PatientRequestDTO patientRequestDTO){
        this.update(patientRequestDTO);
    }
    public void update(PatientRequestDTO patientRequestDTO){
        this.name = patientRequestDTO.name();
        this.form = patientRequestDTO.form();
    }
    public PatientResponseDTO toDTO(){
        if (this.doctor != null) {
            return new PatientResponseDTO(
                    this.name,
                    this.digitalSignatureConsent,
                    this.responsibleDoctor,
                    this.authorizations,
                    this.researches,
                    this.notifications,
                    this.doctor.getName(),
                    this.doctor.getCrm()
            );
        } else {
            return new PatientResponseDTO(
                    this.name,
                    this.digitalSignatureConsent,
                    this.responsibleDoctor,
                    this.authorizations,
                    this.researches,
                    this.notifications,
                    "",
                    ""
            );
        }

    }

}
