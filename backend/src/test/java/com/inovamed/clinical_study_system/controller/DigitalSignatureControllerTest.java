package com.inovamed.clinical_study_system.controller;

import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureRequestDTO;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureResponseDTO;
import com.inovamed.clinical_study_system.service.digital_signature.CreateDigitalSignatureService;
import com.inovamed.clinical_study_system.service.digital_signature.DeactivateDigitalSignatureService;
import com.inovamed.clinical_study_system.service.digital_signature.VerifyDigitalSignatureService;
import com.inovamed.clinical_study_system.service.token.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DigitalSignatureControllerTest {

    @Mock
    private CreateDigitalSignatureService createDigitalSignatureService;

    @Mock
    private VerifyDigitalSignatureService verifyDigitalSignatureService;

    @Mock
    private DeactivateDigitalSignatureService deactivateDigitalSignatureService;

    @Mock
    private TokenService tokenService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private DigitalSignatureController digitalSignatureController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldReturnCreatedStatus_WhenDigitalSignatureIsCreated() throws Exception {
        // Configurações iniciais para o teste
        String token = "Bearer testToken";
        Long userId = 1L;
        byte[] fileContent = "test content".getBytes();

        // Mock do request e serviços
        when(request.getHeader("Authorization")).thenReturn(token);
        when(tokenService.getUserIdFromToken("testToken")).thenReturn(userId);

        DigitalSignatureRequestDTO digitalSignatureRequestDTO = new DigitalSignatureRequestDTO(
                List.of(1L, 2L),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(10)
        );

        MockMultipartFile file = new MockMultipartFile("file", "testFile.txt", "text/plain", fileContent);

        DigitalSignatureResponseDTO responseDTO = new DigitalSignatureResponseDTO(
                "testFile.txt",
                fileContent,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(10),
                true
        );

        when(createDigitalSignatureService.execute(any(DigitalSignatureRequestDTO.class), any(AttachmentRequestDTO.class), anyLong()))
                .thenReturn(responseDTO);

        // Execução do teste
        ResponseEntity<DigitalSignatureResponseDTO> response = digitalSignatureController.create(request, digitalSignatureRequestDTO, file);

        // Verificações
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void verify_ShouldReturnOkStatus_WhenSignatureIsValid() {
        // Mock do serviço de verificação de assinatura
        when(verifyDigitalSignatureService.execute(anyLong())).thenReturn(true);

        // Execução do teste
        ResponseEntity<Boolean> response = digitalSignatureController.verify(1L);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    void deactivate_ShouldReturnNoContentStatus_WhenSignatureIsDeactivated() {
        // Execução do método sem retorno esperado
        doNothing().when(deactivateDigitalSignatureService).execute(anyLong());

        // Execução do teste
        ResponseEntity<?> response = digitalSignatureController.deactivate(1L);

        // Verificações
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
