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
    @OneToMany(mappedBy = "research", cascade = CascadeType.ALL)
    private List<Attachment> attachments;
    @ManyToMany(mappedBy = "researches")
    private List<Patient> patients;
    @ManyToOne
    @JoinColumn(name = "clinical_study_representative_id")
    private ClinicalStudyRepresentative clinicalRepresentative;

    public void update(ResearchUpdateDTO researchUpdateDTO) {
        updateField(() -> this.title = researchUpdateDTO.title(), researchUpdateDTO.title());
        updateField(() -> this.area = researchUpdateDTO.area(), researchUpdateDTO.area());
        updateField(() -> this.numberOfPatients = researchUpdateDTO.numberOfPatients(), researchUpdateDTO.numberOfPatients());
        updateField(() -> this.availableVacancies = researchUpdateDTO.availableVacancies(), researchUpdateDTO.availableVacancies());
        updateField(() -> this.responsibleDoctors = researchUpdateDTO.responsibleDoctors(), researchUpdateDTO.responsibleDoctors());
        updateField(() -> this.institutions = researchUpdateDTO.institutions(), researchUpdateDTO.institutions());
        updateField(() -> this.description = researchUpdateDTO.description(), researchUpdateDTO.description());
        updateField(() -> this.criteria = researchUpdateDTO.criteria(), researchUpdateDTO.criteria());
        updateField(() -> this.studyDuration = researchUpdateDTO.studyDuration(), researchUpdateDTO.studyDuration());
        updateField(() -> this.phases = researchUpdateDTO.phases(), researchUpdateDTO.phases());
        updateField(() -> this.currentPhase = researchUpdateDTO.currentPhase(), researchUpdateDTO.currentPhase());
        updateField(() -> this.location = researchUpdateDTO.location(), researchUpdateDTO.location());
        updateField(() -> this.attachments = researchUpdateDTO.attachments(), researchUpdateDTO.attachments());
    }


    private <T> void updateField(Runnable updateAction, T newValue) {
        if (newValue != null) {
            updateAction.run();
        }
    }

}
