package com.inovamed.clinical_study_system.model.patient;

import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.model.medical_history.MedicalHistory;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "tb_patient")
@Getter
@Setter
public class Patient extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String code = UUID.randomUUID().toString();
    private String name;
    private String gender;
    private LocalDate birth;
    private String phone;
    private Boolean digitalSignatureConsent;
    private Boolean responsibleDoctor;
    @OneToOne
    private DigitalSignature signature;

    @ElementCollection
    private List<String> authorizations;

    @ManyToMany
    @JoinTable(name = "tb_patient_research",joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "research_id"))
    private List<Research> researches;

    @ManyToMany(mappedBy = "recipientsPatients")
    private List<Notification> notifications;

    @OneToOne(cascade = CascadeType.ALL)
    private MedicalHistory medicalHistory;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public void update(PatientUpdateDTO patientUpdateDTO) {
        updateField(() -> this.name = patientUpdateDTO.name(), patientUpdateDTO.name());
        setEmail(patientUpdateDTO.email());
        updateField(() -> this.gender = patientUpdateDTO.gender(), patientUpdateDTO.gender());
        updateField(() -> this.birth = patientUpdateDTO.birth(), patientUpdateDTO.birth());
        updateField(() -> this.phone = patientUpdateDTO.phone(), patientUpdateDTO.phone());
        setPassword(patientUpdateDTO.password());
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
