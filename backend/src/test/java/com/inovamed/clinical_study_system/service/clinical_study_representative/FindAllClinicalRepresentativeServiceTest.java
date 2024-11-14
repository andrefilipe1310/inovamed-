package com.inovamed.clinical_study_system.service.clinical_study_representative;

import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.user.UserRoles;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FindAllClinicalRepresentativeServiceTest {
    @InjectMocks
    private FindAllClinicalRepresentativeService findAllClinicalRepresentativeService;
    @Mock
    private ClinicalStudyRepresentiveRepository clinicalRepository;
    @Mock
    private ClinicalRepresentativeDTOMapperService clinicalRepresentativeDTOMapperService;
    private ClinicalStudyRepresentative clinicalRepresentative;
    private ClinicalStudyRepresentativeResponseDTO responseDTO;


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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startClinicalRepresentative();
    }




    @Test
    void whenFindAllThenReturnAnListOfClinicalRepresentative() {
        //Mockando resultado do repository
        Mockito.when(clinicalRepository.findAll()).thenReturn(List.of(clinicalRepresentative));
        // Mockando mapperDTO
        Mockito.when(clinicalRepresentativeDTOMapperService.toDTO(clinicalRepresentative)).thenReturn(responseDTO);
        List<ClinicalStudyRepresentativeResponseDTO> response = findAllClinicalRepresentativeService.execute();


        assertNotNull(response);
        assertEquals(ID, response.get(0).id());
        assertEquals(NAME, response.get(0).name());
        assertEquals(PHONE, response.get(0).phone());
        assertEquals(CLINICAL_ROLES, response.get(0).clinicalRole());
        assertEquals(EXPERIENCES, response.get(0).experience());
        assertEquals(EMAIL, response.get(0).email());
        assertEquals(PASSWORD, response.get(0).password());
        assertEquals(ROLES, response.get(0).roles());
    }

    @Test
    void whenFindAllAndNoRepresentativesThenReturnEmptyList() {
        // Mockando para retornar uma lista vazia
        Mockito.when(clinicalRepository.findAll()).thenReturn(List.of());
        List<ClinicalStudyRepresentativeResponseDTO> response = findAllClinicalRepresentativeService.execute();

        assertNotNull(response);
        assertTrue(response.isEmpty(), "A lista de representantes deve estar vazia.");
    }
    // instanciando as classes necessarias
    private void startClinicalRepresentative(){
        clinicalRepresentative = new ClinicalStudyRepresentative(ID, NAME, PHONE,CLINICAL_ROLES, EXPERIENCES, RESEARCH, NOTIFICATIONS);
        responseDTO = new ClinicalStudyRepresentativeResponseDTO(ID,NAME,EMAIL,PASSWORD,ROLES,PHONE,CLINICAL_ROLES,EXPERIENCES);

    }
}