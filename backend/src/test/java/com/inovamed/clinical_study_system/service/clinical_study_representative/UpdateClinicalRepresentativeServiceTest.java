package com.inovamed.clinical_study_system.service.clinical_study_representative;

import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.exception.EmailAlreadyRegisteredException;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeUpdateDTO;
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
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
class UpdateClinicalRepresentativeServiceTest {
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
    private UpdateClinicalRepresentativeService updateClinicalRepresentativeService;
    @Mock
    private ClinicalStudyRepresentiveRepository clinicalRepository;
    @Mock
    private ClinicalRepresentativeDTOMapperService clinicalRepresentativeDTOMapperService;

    private ClinicalStudyRepresentative clinicalRepresentative;
   
    private ClinicalStudyRepresentativeUpdateDTO updateDTO;
    private Optional<ClinicalStudyRepresentative> optionalClinicalRepresentative;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        this.startClinicalRepresentative();
    }

    @Test
    void whenUpdateAnReturnSuccess() {
        Mockito.when(clinicalRepository.findById(Mockito.anyLong())).thenReturn(optionalClinicalRepresentative);
        Mockito.when(clinicalRepository.save(Mockito.any(ClinicalStudyRepresentative.class))).thenReturn(clinicalRepresentative);
        Mockito.when(clinicalRepresentativeDTOMapperService.toDTO(Mockito.any(ClinicalStudyRepresentative.class))).thenReturn(new ClinicalStudyRepresentativeResponseDTO(
                ID, "Jonathan", "jonathan@gmail.com", "1234568", ROLES,
                "(82)98999-4231", "Plenor", "neurologista"
        ));;

        ClinicalStudyRepresentativeResponseDTO response = updateClinicalRepresentativeService.execute(ID,updateDTO);

        assertNotNull(response);
        assertEquals("Jonathan",response.name());
        assertEquals("jonathan@gmail.com",response.email());
        assertEquals("1234568",response.password());
        assertEquals("(82)98999-4231",response.phone());
        assertEquals("Plenor",response.clinicalRole());
        assertEquals("neurologista",response.experience());
    }

    @Test
    void whenUpdateWithInvalidIdThenThrowClinicalRepresentativeNotFoundException() {
        Mockito.when(clinicalRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        ClinicalRepresentativeNotFoundException exception = assertThrows(ClinicalRepresentativeNotFoundException.class, () -> {
            updateClinicalRepresentativeService.execute(ID, updateDTO);
        });

        assertEquals("Clinical Representative not found.", exception.getMessage());
    }

    @Test
    void whenCreateWithExistingEmailThenThrowUserAlreadyExistsException() {
        Mockito.when(clinicalRepository.findByEmail(EMAIL)).thenThrow( new EmailAlreadyRegisteredException());
        Mockito.when(clinicalRepository.findById(Mockito.anyLong())).thenReturn(optionalClinicalRepresentative);


        try {
            updateClinicalRepresentativeService.execute(ID,updateDTO);
        }catch (Exception exception){
            assertEquals("Email Already Registered Exception.", exception.getMessage());
            assertThrows(EmailAlreadyRegisteredException.class,()->exception.getClass());
        }


    }




    // instanciando as classes necessarias
    private void startClinicalRepresentative(){
        clinicalRepresentative = new ClinicalStudyRepresentative(ID, NAME, PHONE,CLINICAL_ROLES, EXPERIENCES, RESEARCH, NOTIFICATIONS);
        optionalClinicalRepresentative = Optional.of(new ClinicalStudyRepresentative(ID, NAME, PHONE,CLINICAL_ROLES, EXPERIENCES, RESEARCH, NOTIFICATIONS));
        updateDTO = new ClinicalStudyRepresentativeUpdateDTO("Jonathan","jonathan@gmail.com","1234568","(82)98999-4231","Plenor","neurologista");
      

    }
}