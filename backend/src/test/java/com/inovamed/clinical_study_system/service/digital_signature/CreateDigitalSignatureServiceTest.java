package com.inovamed.clinical_study_system.service.digital_signature;


import com.inovamed.clinical_study_system.exception.InvalidSignatureValidityException;
import com.inovamed.clinical_study_system.exception.PatientNotFoundException;
import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureRequestDTO;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureResponseDTO;
import com.inovamed.clinical_study_system.model.patient.Patient;
import com.inovamed.clinical_study_system.repository.DigitalSignatureRepository;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.*;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateDigitalSignatureServiceTest {

    @InjectMocks
    private CreateDigitalSignatureService createDigitalSignatureService;

    @Mock
    private DigitalSignatureRepository digitalSignatureRepository;

    @Mock
    private DigitalSignatureMapperDTOService digitalSignatureMapperDTOService;

    @Mock
    private PatientRepository patientRepository;

    private KeyPair keyPair;

    public static final Long ID = 1L;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        MockitoAnnotations.openMocks(this);
        // Gerar um par de chaves para os testes
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPair = keyPairGenerator.generateKeyPair();
    }

    @Test
    void execute_ShouldCreateDigitalSignature_WhenValidRequest() throws NoSuchAlgorithmException {
        // Given
        DigitalSignatureRequestDTO requestDTO = new DigitalSignatureRequestDTO( null, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));
        AttachmentRequestDTO attachmentDTO = new AttachmentRequestDTO("file.pdf", new byte[]{1, 2, 3}, 1L);
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setPublicKey(keyPair.getPublic());

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        DigitalSignature digitalSignature = new DigitalSignature();
        when(digitalSignatureRepository.save(any(DigitalSignature.class))).thenReturn(digitalSignature);
        when(digitalSignatureMapperDTOService.toDTO(any(DigitalSignature.class))).thenReturn(new DigitalSignatureResponseDTO("file.pdf", new byte[]{1}, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), true));

        // When
        DigitalSignatureResponseDTO response = createDigitalSignatureService.execute(requestDTO, attachmentDTO,ID);

        // Then
        assertNotNull(response);
        verify(digitalSignatureRepository).save(any(DigitalSignature.class));
        verify(patientRepository).save(patient);
    }

    @Test
    void execute_ShouldThrowInvalidSignatureValidityException_WhenValidFromAfterValidUntil() {
        // Given
        DigitalSignatureRequestDTO requestDTO = new DigitalSignatureRequestDTO( null, LocalDateTime.now().plusDays(1), LocalDateTime.now());
        AttachmentRequestDTO attachmentDTO = new AttachmentRequestDTO("file.pdf", new byte[]{1, 2, 3}, 1L);

        // When & Then
        assertThrows(InvalidSignatureValidityException.class, () -> {
            createDigitalSignatureService.execute(requestDTO, attachmentDTO,ID);
        });
    }

    @Test
    void execute_ShouldThrowPatientNotFoundException_WhenPatientDoesNotExist() {
        // Given
        DigitalSignatureRequestDTO requestDTO = new DigitalSignatureRequestDTO( null, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));
        AttachmentRequestDTO attachmentDTO = new AttachmentRequestDTO("file.pdf", new byte[]{1, 2, 3}, 1L);

        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(PatientNotFoundException.class, () -> {
            createDigitalSignatureService.execute(requestDTO, attachmentDTO,ID);
        });
    }

}
