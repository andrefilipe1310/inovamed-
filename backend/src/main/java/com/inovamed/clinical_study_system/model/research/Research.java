package com.inovamed.clinical_study_system.model.research;

import com.inovamed.clinical_study_system.model.attachment.Attachment;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
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
    @Column(name = "research_id")
    private Long id;
    private int code = (int) Math.random();
    private String title;
    private String area;
    private int numberOfPatients;
    private int availableVacancies;
    @ElementCollection
    private List<String> responsibleDoctors;
    @ElementCollection
    private List<String> institutions;
    private String description;
    @Embedded
    private Criteria criteria;
    @Embedded
    private StudyDuration studyDuration;
    @ElementCollection
    @CollectionTable(name = "research_phases", joinColumns = @JoinColumn(name = "research_id"))
    private List<Phases> phases;
    private int currentPhase;
    private String location;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "research_id")
    private List<Attachment> attachments;
    @ManyToMany(mappedBy = "researches")
    private List<Patient> patients;
    @ManyToOne
    private ClinicalStudyRepresentative clinicalRepresentative;
}
