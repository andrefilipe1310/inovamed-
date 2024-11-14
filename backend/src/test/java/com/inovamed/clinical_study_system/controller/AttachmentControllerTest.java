package com.inovamed.clinical_study_system.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.inovamed.clinical_study_system.exception.MissingSecretKeyException;
import com.inovamed.clinical_study_system.exception.TokenGenerationException;
import com.inovamed.clinical_study_system.model.attachment.AttachmentCreateResponseDTO;
import com.inovamed.clinical_study_system.model.attachment.AttachmentFindResponseDTO;
import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.user.User;
import com.inovamed.clinical_study_system.service.attachment.AttachmentService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@TestPropertySource(properties = "api.security.token.secret=0DpI4MpDup0HbR0Sd4LeRF@")
class AttachmentControllerTest {

    @InjectMocks
    private AttachmentController attachmentController;

    @Mock
    private AttachmentService attachmentService;

    @Mock
    private TokenService tokenService;

    @Mock
    private HttpServletRequest request;

    @Value("${api.security.token.secret:valorPadrao}")
    private String secret;

    private AttachmentCreateResponseDTO attachmentCreateResponseDTO;
    private AttachmentFindResponseDTO attachmentFindResponseDTO;
    private AttachmentRequestDTO attachmentUpdateDTO;
    private String token;

    // Test Constants
    private static final long ID = 1L;
    private static final long USER_ID = 1L;
    private static final String NAME = "John";
    private static final String MESSAGE = "file saved successfully";

    private static final MultipartFile FILE = new MultipartFile() {
        @SuppressWarnings("null")
        @Override
        public String getName() {
            return "";
        }

        @Override
        public String getOriginalFilename() {
            return "";
        }

        @Override
        public String getContentType() {
            return "";
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public long getSize() {
            return 0;
        }

        @SuppressWarnings("null")
        @Override
        public byte[] getBytes() throws IOException {
            return new byte[0];
        }

        @SuppressWarnings("null")
        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public void transferTo(@SuppressWarnings("null") File dest) throws IOException, IllegalStateException {

        }
        // Methods omitted for brevity
    };
    private static final MultipartFile NEW_FILE = new MultipartFile() {
        @SuppressWarnings("null")
        @Override
        public String getName() {
            return "";
        }

        @Override
        public String getOriginalFilename() {
            return "";
        }

        @Override
        public String getContentType() {
            return "";
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public long getSize() {
            return 0;
        }

        @SuppressWarnings("null")
        @Override
        public byte[] getBytes() throws IOException {
            return new byte[2];
        }

        @SuppressWarnings("null")
        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public void transferTo(@SuppressWarnings("null") File dest) throws IOException, IllegalStateException {

        }
        // Methods omitted for brevity
    };
    private static final String NEW_NAME = "John 2";

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        setupTestEntities();
    }

    // Test methods

    @Test
    void uploadReturnsCreatedResponse() {
        Mockito.when(attachmentService.upload(any(AttachmentRequestDTO.class), anyLong())).thenReturn(attachmentCreateResponseDTO);
        Mockito.when(tokenService.getUserIdFromToken(any(String.class))).thenReturn(ID);
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        ResponseEntity<AttachmentCreateResponseDTO> response = attachmentController.upload(request, FILE);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(attachmentCreateResponseDTO, response.getBody());
    }

    @SuppressWarnings("null")
    @Test
    void findAllByIdReturnListOfAttachments() throws IOException {
        Mockito.when(attachmentService.findAllById(anyLong())).thenReturn(List.of(attachmentFindResponseDTO));
        Mockito.when(tokenService.getUserIdFromToken(any(String.class))).thenReturn(ID);
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        ResponseEntity<List<AttachmentFindResponseDTO>> response = attachmentController.findAllById(request);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(attachmentFindResponseDTO, response.getBody().get(0));
    }

    @SuppressWarnings("null")
    @Test
    void findAllReturnsListOfAttachments() throws IOException {
        Mockito.when(attachmentService.findAll()).thenReturn(List.of(attachmentFindResponseDTO));

        ResponseEntity<List<AttachmentFindResponseDTO>> response = attachmentController.findAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(attachmentFindResponseDTO, response.getBody().get(0));
    }

    @Test
    void findByIdReturnsAttachmentSuccessfully() throws IOException {
        Mockito.when(attachmentService.findById(anyLong())).thenReturn(attachmentFindResponseDTO);

        ResponseEntity<AttachmentFindResponseDTO> response = attachmentController.findById(ID);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attachmentFindResponseDTO, response.getBody());
    }

    @Test
    void updateReturnsSuccess() throws IOException {
        AttachmentFindResponseDTO updatedResponseDTO = new AttachmentFindResponseDTO(NEW_NAME, NEW_FILE.getBytes());
        Mockito.when(attachmentService.update(anyLong(), any(AttachmentRequestDTO.class))).thenReturn(updatedResponseDTO);

        ResponseEntity<AttachmentFindResponseDTO> response = attachmentController.update(ID, attachmentUpdateDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedResponseDTO, response.getBody());
    }

    @Test
    void deleteReturnsNoContent() {
        ResponseEntity<?> response = attachmentController.delete(ID);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    // Helper methods

    private void setupTestEntities() throws IOException {
        ClinicalStudyRepresentative clinicalRepresentative = new ClinicalStudyRepresentative(ID, NAME, "123456", "Role", "Experience", List.of(), List.of());
        token = generateToken(clinicalRepresentative);

        attachmentCreateResponseDTO = new AttachmentCreateResponseDTO(NAME, MESSAGE);
        attachmentFindResponseDTO = new AttachmentFindResponseDTO(NAME + " " + ID, FILE.getBytes());
        attachmentUpdateDTO = new AttachmentRequestDTO(NEW_NAME, NEW_FILE.getBytes(), USER_ID);
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
}
