package com.inovamed.clinical_study_system.service.clinical_study_representative;

import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.research.Research;

import com.inovamed.clinical_study_system.model.user.UserRoles;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteByIdClinicalRepresentativeServiceTest {
    public static final long ID = 1L;
    public static final String NAME = "John";
    public static final String PHONE = "(81) 99999-9999";
    public static final String CLINICAL_ROLES = "EXPERT";
    public static final String EXPERIENCES = "neurologist";
    public static final List<Research> RESEARCH = List.of();
    public static final List<Notification> NOTIFICATIONS = List.of();
    public static final String EMAIL = "jonn@gmail.com";
    public static final String PASSWORD = "1234";
    public static final UserRoles ROLES = UserRoles.DOCTOR;

    @InjectMocks
    private DeleteByIdClinicalRepresentativeService deleteByIdClinicalRepresentativeService;

    @Mock
    private ClinicalStudyRepresentiveRepository clinicalRepository;

    @Mock
    private ClinicalRepresentativeDTOMapperService clinicalRepresentativeDTOMapperService;

   

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startClinicalRepresentative();
    }

    @Test
    void deleteWithSuccess() {
        Mockito.when(clinicalRepository.existsById(ID)).thenReturn(true);

        deleteByIdClinicalRepresentativeService.execute(ID);

        Mockito.verify(clinicalRepository, Mockito.times(1)).existsById(ID);
        Mockito.verify(clinicalRepository, Mockito.times(1)).deleteById(ID);
    }

    @Test
    void whenDeleteWithInvalidIdThenThrowClinicalRepresentativeNotFoundException() {
        Mockito.when(clinicalRepository.existsById(ID)).thenReturn(false);

        ClinicalRepresentativeNotFoundException exception = assertThrows(ClinicalRepresentativeNotFoundException.class, () -> {
            deleteByIdClinicalRepresentativeService.execute(ID);
        });

        assertEquals("Clinical representative with ID " + ID + " not found.", exception.getMessage());
    }

    private void startClinicalRepresentative() {
     
    }
}
