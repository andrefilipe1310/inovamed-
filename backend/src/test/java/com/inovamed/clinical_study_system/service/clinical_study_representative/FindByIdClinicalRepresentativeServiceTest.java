package com.inovamed.clinical_study_system.service.clinical_study_representative;

import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FindByIdClinicalRepresentativeServiceTest {
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
    private FindByIdClinicalRepresentativeService findByIdClinicalRepresentativeService;
    @Mock
    private ClinicalStudyRepresentiveRepository clinicalRepository;
    @Mock
    private ClinicalRepresentativeDTOMapperService clinicalRepresentativeDTOMapperService;

    private ClinicalStudyRepresentative clinicalRepresentative;
    private ClinicalStudyRepresentativeRequestDTO requestDTO;
    private ClinicalStudyRepresentativeResponseDTO responseDTO;
    private Optional<ClinicalStudyRepresentative> optionalClinicalRepresentative;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startClinicalRepresentative();
    }

    @Test
    void WhenFindByIdThenReturnAnClinicalRepresentativeInstance() {
        //mockanto resultado do repository
        Mockito.when(clinicalRepository.findById(Mockito.anyLong())).thenReturn(optionalClinicalRepresentative);
        //mockanto resultado do dtoMapperService
        Mockito.when(clinicalRepresentativeDTOMapperService.toDTO(optionalClinicalRepresentative.get())).thenReturn(responseDTO);

        ClinicalStudyRepresentativeResponseDTO response = findByIdClinicalRepresentativeService.execute(ID);

        assertNotNull(response);
        Assertions.assertEquals(ClinicalStudyRepresentativeResponseDTO.class,response.getClass());
        assertEquals(ClinicalStudyRepresentativeResponseDTO.class, response.getClass());
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
    void WhenFindByIdReturnAnClinicalRepresentativeNotFoundException(){
        Mockito.when(clinicalRepository.findById(Mockito.anyLong())).thenThrow( new ClinicalRepresentativeNotFoundException());
        try {
            clinicalRepository.findById(ID);
        } catch (Exception e){
          assertEquals(ClinicalRepresentativeNotFoundException.class, e.getClass());
        }
    }

    // instanciando as classes necessarias
    private void startClinicalRepresentative(){
        clinicalRepresentative = new ClinicalStudyRepresentative(ID, NAME, PHONE,CLINICAL_ROLES, EXPERIENCES, RESEARCH, NOTIFICATIONS);
        optionalClinicalRepresentative = Optional.of(new ClinicalStudyRepresentative(ID, NAME, PHONE,CLINICAL_ROLES, EXPERIENCES, RESEARCH, NOTIFICATIONS));
        requestDTO = new ClinicalStudyRepresentativeRequestDTO(ID,NAME, EMAIL, PASSWORD, ROLES,PHONE,CLINICAL_ROLES,EXPERIENCES);
        responseDTO = new ClinicalStudyRepresentativeResponseDTO(ID,NAME,EMAIL,PASSWORD,ROLES,PHONE,CLINICAL_ROLES,EXPERIENCES);

    }
}