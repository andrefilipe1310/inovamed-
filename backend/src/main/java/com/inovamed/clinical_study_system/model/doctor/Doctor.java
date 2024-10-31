package com.inovamed.clinical_study_system.model.doctor;
import com.inovamed.clinical_study_system.model.application.Application;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.patient.Patient;
import com.inovamed.clinical_study_system.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity(name = "tb_doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id;
    @Column(unique = true, nullable = false,name = "key_doctor")
    private String key = UUID.randomUUID().toString();

    private String name;
    private String clinic;
    private String specialty;
    @Enumerated(EnumType.STRING)
    private DoctorExperienceEnum doctorExperienceEnum;
    private String crm;
    private String phone;


    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Application> applicationsSubmitted;

    @ManyToMany(mappedBy = "recipientsDoctors")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "doctor")
    private List<Patient> patients;

    public void update(DoctorUpdateDTO doctorUpdateDTO) {
        updateField(() -> this.name = doctorUpdateDTO.name(), doctorUpdateDTO.name());
        if (doctorUpdateDTO.email() != null) setEmail(doctorUpdateDTO.email());

        updateField(() -> this.clinic = doctorUpdateDTO.clinic(), doctorUpdateDTO.clinic());
        updateField(() -> this.specialty = doctorUpdateDTO.specialty(), doctorUpdateDTO.specialty());
        updateField(() -> this.doctorExperienceEnum = doctorUpdateDTO.doctorExperienceEnum(), doctorUpdateDTO.doctorExperienceEnum());
        updateField(() -> this.crm = doctorUpdateDTO.crm(), doctorUpdateDTO.crm());
        updateField(() -> this.phone = doctorUpdateDTO.phone(), doctorUpdateDTO.phone());
        if (doctorUpdateDTO.password() != null) setPassword(doctorUpdateDTO.password());
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


