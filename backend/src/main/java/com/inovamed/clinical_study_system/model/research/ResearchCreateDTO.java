package com.inovamed.clinical_study_system.model.research;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResearchCreateDTO {
    private String title;
    private String area;
    private Integer numberOfPatients;
    private Integer availableVacancies;
    private List<String> responsibleDoctors;
    private List<String> institutions;
    private String description;
    private String criteria;
    private LocalDate start_date;
    private LocalDate end_date;
    private String phases;
    private String currentPhase;
    private String location;
}
