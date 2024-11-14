package com.inovamed.clinical_study_system.service.digital_signature;

import com.inovamed.clinical_study_system.exception.SignatureIsInactiveException;
import com.inovamed.clinical_study_system.exception.SignatureNotFoundException;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.repository.DigitalSignatureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeactivateDigitalSignatureServiceTest {

    @Mock
    private DigitalSignatureRepository digitalSignatureRepository;

    @InjectMocks
    private DeactivateDigitalSignatureService deactivateDigitalSignatureService;

    private DigitalSignature digitalSignature;

    @BeforeEach
    void setUp() {
        digitalSignature = new DigitalSignature();
        digitalSignature.setId(1L);
        digitalSignature.setActive(true);
    }

    @Test
    void execute_ShouldDeactivateSignature_WhenSignatureIsActive() {
        // Configura o mock para encontrar a assinatura ativa
        when(digitalSignatureRepository.findById(1L)).thenReturn(Optional.of(digitalSignature));

        // Executa o serviço
        deactivateDigitalSignatureService.execute(1L);

        // Verifica que o estado ativo foi alterado para false
        assert !digitalSignature.isActive();
        // Verifica que o método save foi chamado
        verify(digitalSignatureRepository).save(digitalSignature);
    }

    @Test
    void execute_ShouldThrowSignatureIsInactiveException_WhenSignatureIsAlreadyInactive() {
        // Define a assinatura como inativa
        digitalSignature.setActive(false);
        when(digitalSignatureRepository.findById(1L)).thenReturn(Optional.of(digitalSignature));

        // Verifica que a exceção é lançada ao tentar desativar uma assinatura já inativa
        assertThrows(SignatureIsInactiveException.class, () -> deactivateDigitalSignatureService.execute(1L));

        // Verifica que o método save nunca foi chamado
        verify(digitalSignatureRepository, never()).save(digitalSignature);
    }

    @Test
    void execute_ShouldThrowSignatureNotFoundException_WhenSignatureDoesNotExist() {
        // Configura o mock para não encontrar a assinatura
        when(digitalSignatureRepository.findById(1L)).thenReturn(Optional.empty());

        // Verifica que a exceção é lançada ao tentar desativar uma assinatura inexistente
        assertThrows(SignatureNotFoundException.class, () -> deactivateDigitalSignatureService.execute(1L));

        // Verifica que o método save nunca foi chamado
        verify(digitalSignatureRepository, never()).save(any());
    }
}
