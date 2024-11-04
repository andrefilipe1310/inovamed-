package com.inovamed.clinical_study_system.model.research;

import com.inovamed.clinical_study_system.model.attachment.Attachment;

import java.util.List;

public record ResearchRequestDTO(String title, String area, int numberOfPatients, int availableVacancies,
                                 List<String> responsibleDoctors,List<String> institutions,
                                 String description,Criteria criteria,StudyDuration studyDuration,String phases,
                                 int currentPhase,String location
                                ) {
}
