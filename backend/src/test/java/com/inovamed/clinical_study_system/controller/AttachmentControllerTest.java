package com.inovamed.clinical_study_system.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.inovamed.clinical_study_system.exception.MissingSecretKeyException;
import com.inovamed.clinical_study_system.exception.TokenGenerationException;
import com.inovamed.clinical_study_system.model.attachment.AttachmentCreateResponseDTO;
import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.user.User;
import com.inovamed.clinical_study_system.service.attachment.AttachmentService;
import com.inovamed.clinical_study_system.service.token.TokenService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
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


import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(properties = "api.security.token.secret=0DpI4MpDup0HbR0Sd4LeRF@")
class AttachmentControllerTest {

    @InjectMocks
    AttachmentController attachmentController;

    @Mock
    AttachmentService attachmentService;

    @Mock
    TokenService tokenService;

    @Mock
    HttpServletRequest request;

    @Value("${api.security.token.secret:valorPadrao}")
    private String secret;

    AttachmentCreateResponseDTO attachmentCreateResponseDTO;
    AttachmentRequestDTO attachmentRequestDTO;
    String token;
    ClinicalStudyRepresentative clinicalRepresentative;

    public static final long ID = 1L;
    public static final String NAME = "John";
    public static final String PHONE = "(81) 99999-9999";
    public static final String CLINICAL_ROLES = "EXPERT";
    public static final String EXPERIENCES = "neurologist";
    public static final List<Research> RESEARCH = List.of();
    public static final List<Notification> NOTIFICATIONS = List.of();
    public static final String MESSAGE = "file saved successfully";
    public static  final MultipartFile FILE = new MultipartFile() {
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

        @Override
        public byte[] getBytes() throws IOException {
            return new byte[0];
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {

        }
    };

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startAttachment();

    }

    @Test
    void uploadReturnsCreatedResponse() {
        Mockito.when(attachmentService.upload(Mockito.any(AttachmentRequestDTO.class),Mockito.anyLong())).thenReturn(attachmentCreateResponseDTO);
        Mockito.when(tokenService.getUserIdFromToken(Mockito.anyString())).thenReturn(ID);
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

       ResponseEntity<AttachmentCreateResponseDTO> response = attachmentController.upload(request,FILE);

       assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    void findAllById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private String generateToken(User user){

        if (this.secret == null || this.secret.isEmpty()) {
            throw new MissingSecretKeyException();
        }
        try {

            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            String token = JWT.create().withIssuer("INOVAMED")
                    .withSubject(user.getEmail()).withClaim("userId", user.getId())
                    .withExpiresAt(genExpirationDate()).sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new TokenGenerationException(exception);
        }
    }
    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    void startAttachment(){
        clinicalRepresentative = new ClinicalStudyRepresentative(ID, NAME, PHONE,CLINICAL_ROLES, EXPERIENCES, RESEARCH, NOTIFICATIONS);
        token = this.generateToken(clinicalRepresentative);

        attachmentCreateResponseDTO = new AttachmentCreateResponseDTO(NAME,MESSAGE);
    }
}