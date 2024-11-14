package com.inovamed.clinical_study_system.service.clinical_study_representative;

import com.inovamed.clinical_study_system.exception.UserAlreadyExistsException;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeRequestDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.user.UserRoles;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.junit.jupiter.api.Assertions;
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
class CreateClinicalRepresentativeServiceTest {
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
    private CreateClinicalRepresentativeService createClinicalRepresentativeService;
    @Mock
    private ClinicalStudyRepresentiveRepository clinicalRepository;
    @Mock
    private ClinicalRepresentativeDTOMapperService clinicalRepresentativeDTOMapperService;

    private ClinicalStudyRepresentative clinicalRepresentative;
    private ClinicalStudyRepresentativeRequestDTO requestDTO;
    private ClinicalStudyRepresentativeResponseDTO responseDTO;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        this.startClinicalRepresentative();
    }
    @Test
    void whenCreateThenReturnSuccess(){
        //mockanto resultado do repository
        Mockito.when(clinicalRepository.save(clinicalRepresentative)).thenReturn(clinicalRepresentative);
        //mockanto resultado do dtoMapperService
        Mockito.when(clinicalRepresentativeDTOMapperService.toEntity(requestDTO)).thenReturn(clinicalRepresentative);
        Mockito.when(clinicalRepresentativeDTOMapperService.toDTO(clinicalRepresentative)).thenReturn(responseDTO);

        ClinicalStudyRepresentativeResponseDTO response = createClinicalRepresentativeService.execute(requestDTO);

        assertNotNull(response);
        Assertions.assertEquals(ClinicalStudyRepresentativeResponseDTO.class,response.getClass());
        assertEquals(ID, response.id());
        assertEquals(NAME, response.name());
        assertEquals(PHONE, response.phone());
        assertEquals(CLINICAL_ROLES, response.clinicalRole());
        assertEquals(EXPERIENCES, response.experience());
        assertEquals(EMAIL, response.email());
        assertEquals(PASSWORD, response.password());
        assertEquals(ROLES, response.roles());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException(){
        Mockito.when(clinicalRepository.findByEmail(Mockito.anyString())).thenThrow( new UserAlreadyExistsException());

        try{

            clinicalRepository.findByEmail(EMAIL);

        } catch (Exception exception) {
            assertEquals(UserAlreadyExistsException.class, exception.getClass());
            assertEquals("User already exists.",exception.getMessage());
        }
    }


    // instanciando as classes necessarias
    private void startClinicalRepresentative(){
        clinicalRepresentative = new ClinicalStudyRepresentative(ID, NAME, PHONE,CLINICAL_ROLES, EXPERIENCES, RESEARCH, NOTIFICATIONS);

        requestDTO = new ClinicalStudyRepresentativeRequestDTO(ID,NAME, EMAIL, PASSWORD, ROLES,PHONE,CLINICAL_ROLES,EXPERIENCES);
        responseDTO = new ClinicalStudyRepresentativeResponseDTO(ID,NAME,EMAIL,PASSWORD,ROLES,PHONE,CLINICAL_ROLES,EXPERIENCES);
        

        }
}