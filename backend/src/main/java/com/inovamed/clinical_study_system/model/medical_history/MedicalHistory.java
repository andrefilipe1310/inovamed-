package com.inovamed.clinical_study_system.model.medical_history;


import com.inovamed.clinical_study_system.model.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_medical_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String text;
    @OneToOne(mappedBy = "medicalHistory")
    Patient patient;
}
