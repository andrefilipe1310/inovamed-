package com.inovamed.clinical_study_system.model.research;



import java.time.LocalDate;
import java.util.List;

public record ResearchRequestDTO(String title, String area, int numberOfPatients, int availableVacancies,
                                 List<String> responsibleDoctors, List<String> institutions,
                                 String description, Criteria criteria, LocalDate start_date,
                                 LocalDate end_date , String phases,
                                 int currentPhase, String location
                                ) {
}
