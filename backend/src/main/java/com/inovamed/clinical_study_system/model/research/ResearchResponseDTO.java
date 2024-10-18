package com.inovamed.clinical_study_system.model.research;

import com.inovamed.clinical_study_system.model.attachment.Attachment;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.patient.Patient;

import java.util.List;

public record ResearchResponseDTO(int code, String title, String area, int numberOfPatients,
                                  int availableVacancies, List<String> responsibleDoctors,
                                  List<String> institutions, String description,
                                  Criteria criteria, StudyDuration studyDuration,
                                  List<Phases> phases,
                                  int currentPhase, String location,
                                  List<Attachment> attachments, List<Patient> patients,
                                  ClinicalStudyRepresentative clinicalRepresentative) {

}
