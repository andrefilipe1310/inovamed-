package com.inovamed.clinical_study_system.model.research;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResearchRequestDTO{
    private String title;
    private String area;
    private Integer numberOfPatients;
    private Integer availableVacancies;
    private List<String> responsibleDoctors;
    private List<String> institutions;
    private String description;
    private Criteria criteria;
    private LocalDate start_date;
    private LocalDate end_date;
    private List<Phases> phases;
    private Integer currentPhase;
    private String location;

    public ResearchRequestDTO(ResearchCreateDTO researchCreateDTO, Criteria criteria, List<Phases> phases){

        this.title = researchCreateDTO.getTitle();
        this.area = researchCreateDTO.getArea();
        this.numberOfPatients = researchCreateDTO.getNumberOfPatients();
        this.availableVacancies = researchCreateDTO.getAvailableVacancies();
        this.responsibleDoctors = researchCreateDTO.getResponsibleDoctors();
        this.institutions = researchCreateDTO.getInstitutions();
        this.description = researchCreateDTO.getDescription();
        this.criteria = criteria;
        this.phases = phases;
        this.start_date = researchCreateDTO.getStart_date();
        this.end_date = researchCreateDTO.getEnd_date();
        this.currentPhase =  Integer.valueOf(researchCreateDTO.getCurrentPhase());
        this.location = getLocation();
    }
}
