package com.inovamed.clinical_study_system.model.research;

import java.util.List;

public record FindPatientsAndDoctorIdsResponseDTO(
        List<Long> doctorsId,
        List<Long> patientsId
) {
}
