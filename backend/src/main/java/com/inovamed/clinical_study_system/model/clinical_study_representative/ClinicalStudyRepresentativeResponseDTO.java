package com.inovamed.clinical_study_system.model.clinical_study_representative;

import com.inovamed.clinical_study_system.model.user.UserRoles;

public record ClinicalStudyRepresentativeResponseDTO(Long id, String name, String email, String password, UserRoles roles, String phone, String clinicalRole, String experience ) {
}
