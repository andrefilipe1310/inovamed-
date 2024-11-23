package com.inovamed.clinical_study_system.model.application;


import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.patient.Patient;
import com.inovamed.clinical_study_system.model.research.Research;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity(name = "tb_application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @Column(columnDefinition = "TEXT")
    String message;
    @Enumerated(EnumType.STRING)
    private StatusApplication statusApplication;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "research_id", nullable = false)
    private Research research;


}
