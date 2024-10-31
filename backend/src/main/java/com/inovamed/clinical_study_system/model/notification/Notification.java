package com.inovamed.clinical_study_system.model.notification;

import com.inovamed.clinical_study_system.model.attachment.Attachment;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity(name = "tb_notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    private Long senderCode;
    private String title;
    private String message;

    @ManyToOne
    @JoinColumn(name = "clinical_study_representative_id")
    private ClinicalStudyRepresentative studyRepresentative;
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    private List<Attachment> attachment;

    @ManyToMany
    @JoinTable(name = "notification_recipients_patients",
            joinColumns = @JoinColumn(name = "notification_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Patient> recipientsPatients;

    @ManyToMany
    @JoinTable(name = "notification_recipients_doctors",
            joinColumns = @JoinColumn(name = "notification_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Doctor> recipientsDoctors;

}
