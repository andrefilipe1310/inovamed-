package com.inovamed.clinical_study_system.service.digital_signature;


import com.inovamed.clinical_study_system.exception.SignatureErrorVerifyException;
import com.inovamed.clinical_study_system.exception.SignatureNotFoundException;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.model.user.User;
import com.inovamed.clinical_study_system.repository.DigitalSignatureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VerifyDigitalSignatureServiceTest {

    @Mock
    private DigitalSignatureRepository digitalSignatureRepository;
    @Spy
    @InjectMocks
    private VerifyDigitalSignatureService verifyDigitalSignatureService;

    private DigitalSignature digitalSignature;
    private PrivateKey privateKey;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        User user = new User();
        digitalSignature = new DigitalSignature();
        digitalSignature.setDocumentContent(new byte[]{1, 2, 3});
        digitalSignature.setSignature(new byte[]{4, 5, 6});
        digitalSignature.setUser(user);

        PublicKey publicKey = mock(PublicKey.class);
        digitalSignature.getUser().setPublicKey(publicKey);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        user.setPublicKey(keyPair.getPublic());
        privateKey = keyPair.getPrivate();
        digitalSignature.setUser(user);
    }

    @Test
    void execute_ShouldReturnTrue_WhenSignatureIsValid() throws Exception {
        when(digitalSignatureRepository.findById(anyLong())).thenReturn(Optional.of(digitalSignature));

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(digitalSignature.getDocumentContent());
        digitalSignature.setSignature(signature.sign());

        boolean result = verifyDigitalSignatureService.execute(1L);
        assertTrue(result);
    }

    @Test
    void execute_ShouldThrowSignatureNotFoundException_WhenSignatureNotFound() {
        when(digitalSignatureRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(SignatureNotFoundException.class, () -> verifyDigitalSignatureService.execute(1L));
    }

    @Test
    void execute_ShouldThrowSignatureErrorVerifyException_WhenVerificationFails() throws Exception {
        when(digitalSignatureRepository.findById(anyLong())).thenReturn(Optional.of(digitalSignature));

        // Espionar e forçar exceção em verifyDocumentSignature
        doThrow(new Exception("Erro de verificação"))
                .when(verifyDigitalSignatureService).verifyDocumentSignature(
                        digitalSignature.getDocumentContent(),
                        digitalSignature.getSignature(),
                        digitalSignature.getUser().getPublicKey()
                );

        assertThrows(SignatureErrorVerifyException.class, () -> verifyDigitalSignatureService.execute(1L));
    }
}

