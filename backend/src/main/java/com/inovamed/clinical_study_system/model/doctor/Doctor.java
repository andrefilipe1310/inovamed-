package com.inovamed.clinical_study_system.model.doctor;
import com.inovamed.clinical_study_system.model.application.Application;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "tb_doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String key;

    private String name;
    private String email;
    private String clinic;
    private String specialty;
    @Enumerated(EnumType.STRING)
    private DoctorExperienceEnum doctorExperienceEnum;
    private String crm;
    private String phone;
    private String password;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Application> applicationsSubmitted;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "doctor")
    private List<Patient> patients;

    public void update(DoctorUpdateDTO doctorUpdateDTO) {
        updateField(() -> this.name = doctorUpdateDTO.name(), doctorUpdateDTO.name());
        updateField(() -> this.email = doctorUpdateDTO.email(), doctorUpdateDTO.email());
        updateField(() -> this.clinic = doctorUpdateDTO.clinic(), doctorUpdateDTO.clinic());
        updateField(() -> this.specialty = doctorUpdateDTO.specialty(), doctorUpdateDTO.specialty());
        updateField(() -> this.doctorExperienceEnum = doctorUpdateDTO.doctorExperienceEnum(), doctorUpdateDTO.doctorExperienceEnum());
        updateField(() -> this.crm = doctorUpdateDTO.crm(), doctorUpdateDTO.crm());
        updateField(() -> this.phone = doctorUpdateDTO.phone(), doctorUpdateDTO.phone());
        updateField(() -> this.password = doctorUpdateDTO.password(), doctorUpdateDTO.password()); // Criptografar a senha, se necessário
    }
    private <T> void updateField(Runnable updateAction, T newValue) {
        if (newValue != null) {
            updateAction.run();
        }
    }

    public void registerPatient() {
    }

    public void approvePatient() {
    }



}


