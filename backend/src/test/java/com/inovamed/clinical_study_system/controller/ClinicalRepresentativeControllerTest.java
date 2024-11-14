package com.inovamed.clinical_study_system.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.inovamed.clinical_study_system.exception.MissingSecretKeyException;
import com.inovamed.clinical_study_system.exception.TokenGenerationException;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeRequestDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeUpdateDTO;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.user.User;
import com.inovamed.clinical_study_system.model.user.UserRoles;
import com.inovamed.clinical_study_system.service.clinical_study_representative.*;
import com.inovamed.clinical_study_system.service.token.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = "api.security.token.secret=0DpI4MpDup0HbR0Sd4LeRF@")
class ClinicalRepresentativeControllerTest {

    @InjectMocks
    private ClinicalRepresentativeController clinicalRepresentativeController;

    @Mock
    private CreateClinicalRepresentativeService createClinicalRepresentativeService;

    @Mock
    private FindAllClinicalRepresentativeService findAllClinicalRepresentativeService;

    @Mock
    private FindByIdClinicalRepresentativeService findByIdClinicalRepresentativeService;

    @Mock
    private DeleteByIdClinicalRepresentativeService deleteByIdClinicalRepresentativeService;

    @Mock
    private UpdateClinicalRepresentativeService updateClinicalRepresentativeService;

    @Mock
    private TokenService tokenService;

    @Mock
    private HttpServletRequest request;
    @Value("${api.security.token.secret:valorPadrao}")
    private String secret;


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



    private ClinicalStudyRepresentativeRequestDTO requestDTO;
    private ClinicalStudyRepresentativeResponseDTO responseDTO;
    private ClinicalStudyRepresentativeUpdateDTO updateDTO;
    private String token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startClinicalRepresentative();
       
    }

    @Test
    void create_ReturnsCreatedResponse() throws Exception {

        when(createClinicalRepresentativeService.execute(any(ClinicalStudyRepresentativeRequestDTO.class)))
                .thenReturn(responseDTO);

        ResponseEntity<ClinicalStudyRepresentativeResponseDTO> response = clinicalRepresentativeController.create(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(createClinicalRepresentativeService, times(1)).execute(requestDTO);
    }

    @Test
    void findAll_ReturnsListOfRepresentatives() throws Exception {

        List<ClinicalStudyRepresentativeResponseDTO> responseList = Collections.singletonList(responseDTO);

        when(findAllClinicalRepresentativeService.execute()).thenReturn(responseList);

        ResponseEntity<List<ClinicalStudyRepresentativeResponseDTO>> response = clinicalRepresentativeController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseList, response.getBody());
        verify(findAllClinicalRepresentativeService, times(1)).execute();
    }

    @Test
    void findById_ReturnsRepresentative_WhenIdExists() throws Exception {

        when(findByIdClinicalRepresentativeService.execute(anyLong())).thenReturn(responseDTO);
        Mockito.when(tokenService.getUserIdFromToken(any(String.class))).thenReturn(ID);
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        ResponseEntity<ClinicalStudyRepresentativeResponseDTO> response = clinicalRepresentativeController.findById(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(findByIdClinicalRepresentativeService, times(1)).execute(1L);
    }

    @Test
    void update_ReturnsUpdatedRepresentative() throws Exception {

        when(updateClinicalRepresentativeService.execute(anyLong(), any(ClinicalStudyRepresentativeUpdateDTO.class)))
                .thenReturn(responseDTO);

        ResponseEntity<ClinicalStudyRepresentativeResponseDTO> response = clinicalRepresentativeController.update(1L, updateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(updateClinicalRepresentativeService, times(1)).execute(1L, updateDTO);
    }

    @Test
    void delete_ReturnsNoContent_WhenIdExists() throws Exception {
        doNothing().when(deleteByIdClinicalRepresentativeService).execute(anyLong());

        ResponseEntity<Void> response = clinicalRepresentativeController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deleteByIdClinicalRepresentativeService, times(1)).execute(1L);
    }

    private String generateToken(User user) {
        if (secret == null || secret.isEmpty()) {
            throw new MissingSecretKeyException();
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer("INOVAMED").withSubject(user.getEmail()).withClaim("userId", user.getId())
                    .withExpiresAt(genExpirationDate()).sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new TokenGenerationException(exception);
        }
    }
    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    private void startClinicalRepresentative(){
        ClinicalStudyRepresentative clinicalRepresentative = new ClinicalStudyRepresentative(ID, NAME, "123456", "Role", "Experience", List.of(), List.of());
        requestDTO = new ClinicalStudyRepresentativeRequestDTO(ID,NAME, EMAIL, PASSWORD, ROLES,PHONE,CLINICAL_ROLES,EXPERIENCES);
        responseDTO = new ClinicalStudyRepresentativeResponseDTO(ID,NAME,EMAIL,PASSWORD,ROLES,PHONE,CLINICAL_ROLES,EXPERIENCES);
        updateDTO = new ClinicalStudyRepresentativeUpdateDTO("Jonathan","jonathan@gmail.com","1234568","(82)98999-4231","Plenor","neurologista");
        token = generateToken(clinicalRepresentative);
    }
}
