package com.inovamed.clinical_study_system.model.application;


import com.inovamed.clinical_study_system.model.doctor.Doctor;
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
    private Long patientCode;
    private Long doctorCode;
    @ManyToOne // Adicionando a relação de muitos-para-um
    @JoinColumn(name = "doctor_id", nullable = false) // Nome da coluna que vai referenciar o médico
    private Doctor doctor;


}
