package com.inovamed.clinical_study_system.service.clinical_study_representative;

import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeRequestDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FindByIdClinicalRepresentativeServiceTest {
    @Mock
    private ClinicalStudyRepresentiveRepository clinicalRepository;
    @Autowired
    private ClinicalRepresentativeDTOMapperService clinicalRepresentativeDTOMapperService;

    private ClinicalStudyRepresentative clinicalRepresentative;
    private ClinicalStudyRepresentativeRequestDTO requestDTO;
    private ClinicalStudyRepresentativeResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
    }

    @Test
    void execute() {
    }
}