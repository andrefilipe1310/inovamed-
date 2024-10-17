package com.inovamed.clinical_study_system.model.patient;

import com.inovamed.clinical_study_system.model.medical_history.MedicalHistory;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.notification.Notification;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "tb_patient")
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;
    private String name;
    private String email;
    private String gender;
    private LocalDate birth;
    private String phone;
    private String password;
    private Boolean digitalSignatureConsent;
    private Boolean responsibleDoctor;
    private List<String> authorizations;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Research> researches;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Notification> notifications;
    @OneToOne(cascade = CascadeType.ALL)
    private MedicalHistory medicalHistory;
    private String signature;
    @OneToOne
    private Doctor doctor;

    public void update(PatientUpdateDTO patientUpdateDTO) {
        updateField(() -> this.name = patientUpdateDTO.name(), patientUpdateDTO.name());
        updateField(() -> this.email = patientUpdateDTO.email(), patientUpdateDTO.email());
        updateField(() -> this.gender = patientUpdateDTO.gender(), patientUpdateDTO.gender());
        updateField(() -> this.birth = patientUpdateDTO.birth(), patientUpdateDTO.birth());
        updateField(() -> this.phone = patientUpdateDTO.phone(), patientUpdateDTO.phone());
        updateField(() -> this.password = patientUpdateDTO.password(), patientUpdateDTO.password()); // Criptografar a senha
        updateField(() -> this.digitalSignatureConsent = patientUpdateDTO.digitalSignatureConsent(), patientUpdateDTO.digitalSignatureConsent());
        updateField(() -> this.responsibleDoctor = patientUpdateDTO.responsibleDoctor(), patientUpdateDTO.responsibleDoctor());
        updateField(() -> this.authorizations = patientUpdateDTO.authorizations(), patientUpdateDTO.authorizations());
        updateField(() -> this.researches = patientUpdateDTO.researches(), patientUpdateDTO.researches());
        updateField(() -> this.notifications = patientUpdateDTO.notifications(), patientUpdateDTO.notifications());
        updateField(() -> this.medicalHistory = patientUpdateDTO.medicalHistory(), patientUpdateDTO.medicalHistory());
        updateField(() -> this.signature = patientUpdateDTO.signature(), patientUpdateDTO.signature());
    }

    private <T> void updateField(Runnable updateAction, T newValue) {
        if (newValue != null) {
            updateAction.run();
        }
    }
}
