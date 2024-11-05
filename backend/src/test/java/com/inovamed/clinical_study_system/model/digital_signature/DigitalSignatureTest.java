package com.inovamed.clinical_study_system.model.digital_signature;

import com.inovamed.clinical_study_system.model.consent.Consent;
import com.inovamed.clinical_study_system.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DigitalSignatureTest {

    private DigitalSignature digitalSignature;
    private User user;
    private List<Consent> consents;
    private byte[] documentContent = "test document content".getBytes();
    private byte[] signature = "test signature".getBytes();

    @BeforeEach
    void setUp() {
        user = new User(); // Inicialize o objeto User conforme necessário
        consents = List.of(new Consent()); // Inicialize a lista de consents conforme necessário

        digitalSignature = new DigitalSignature(
                1L,
                "Test Document",
                documentContent,
                signature,
                consents,
                LocalDateTime.now(),
                LocalDateTime.now().minusDays(1), // validade começando ontem
                LocalDateTime.now().plusDays(1), // validade expira amanhã
                true,
                user
        );
    }

    @Test
    void deactivateIfExpired_ShouldDeactivate_WhenValidUntilIsPast() {
        // Configura o campo validUntil para o passado e chama o método
        digitalSignature.setValidUntil(LocalDateTime.now().minusDays(1)); // Expirou ontem
        digitalSignature.deactivateIfExpired();

        // Verifica se isActive foi setado para false
        assertFalse(digitalSignature.isActive());
    }

    @Test
    void deactivateIfExpired_ShouldRemainActive_WhenValidUntilIsFuture() {
        // Configura o campo validUntil para uma data futura e chama o método
        digitalSignature.setValidUntil(LocalDateTime.now().plusDays(1)); // Expira amanhã
        digitalSignature.deactivateIfExpired();

        // Verifica se isActive continua true
        assertTrue(digitalSignature.isActive());
    }

    @Test
    void constructor_ShouldInitializeFieldsCorrectly() {
        // Verifica se todos os campos foram inicializados corretamente
        assertTrue(digitalSignature.getDocumentName().equals("Test Document"));
        assertTrue(digitalSignature.getDocumentContent().length > 0);
        assertTrue(digitalSignature.getSignature().length > 0);
        assertTrue(digitalSignature.getConsents().equals(consents));
        assertTrue(digitalSignature.getUser().equals(user));
        assertTrue(digitalSignature.isActive());
    }
}
