package com.inovamed.clinical_study_system.model.research;

import com.inovamed.clinical_study_system.model.attachment.Attachment;
import com.inovamed.clinical_study_system.model.patient.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "tb_research")
@Getter
@Setter
@NoArgsConstructor
public class Research {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int code;
    private String title;
    private String area;
    private int numberOfPatients;
    private int availableVacancies;
    private List<String> responsibleDoctors;
    private List<String> institutions;
    private String description;
    @Embedded
    private Criteria criteria;
    @Embedded
    private StudyDuration studyDuration;
    @Embedded
    private List<Phases> phases;
    private int currentPhase;
    private String location;
    @OneToMany
    private List<Attachment> attachments;
    @OneToMany
    private List<Patient> patients;
}
