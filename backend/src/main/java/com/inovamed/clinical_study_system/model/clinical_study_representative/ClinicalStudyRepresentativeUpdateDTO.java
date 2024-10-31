package com.inovamed.clinical_study_system.model.clinical_study_representative;


public record ClinicalStudyRepresentativeUpdateDTO(String name,String email,String password,String phone
        ,String clinicalRole,String experiences) {
}
