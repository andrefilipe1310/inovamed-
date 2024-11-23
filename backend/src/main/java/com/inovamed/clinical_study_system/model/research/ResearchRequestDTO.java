package com.inovamed.clinical_study_system.model.research;



import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ResearchRequestDTO{
    private String title;
    private String area;
    private Integer numberOfPatients;
    private Integer availableVacancies;
    private List<String> responsibleDoctors;
    private List<String> institutions;
    private String description;
    private Criteria criteria;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate start_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate end_date;
    private List<Phases> phases;
    private Integer currentPhase;
    private String location;
}
