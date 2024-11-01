package com.inovamed.clinical_study_system.service.digital_signature;

import com.inovamed.clinical_study_system.exception.UserNotFoundException;
import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureRequestDTO;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureResponseDTO;
import com.inovamed.clinical_study_system.model.user.User;
import com.inovamed.clinical_study_system.repository.DigitalSignatureRepository;
import com.inovamed.clinical_study_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.*;
import java.time.LocalDateTime;

@Service
public class CreateDigitalSignatureService {

    @Autowired
    private DigitalSignatureRepository digitalSignatureRepository;
    @Autowired
    private DigitalSignatureMapperDTOService digitalSignatureMapperDTOService;

    @Autowired
    private UserRepository userRepository;

    public DigitalSignatureResponseDTO execute(DigitalSignatureRequestDTO digitalSignatureRequestDTO, AttachmentRequestDTO attachmentRequestDTO) {
        User user = userRepository.findById(digitalSignatureRequestDTO.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + digitalSignatureRequestDTO.userId()));

        KeyPair keyPair = generateKeyPair();
        byte[] signature = signDocument(attachmentRequestDTO.getArchive(), keyPair.getPrivate());

        DigitalSignature digitalSignature = new DigitalSignature();
        digitalSignature.setDocumentName(attachmentRequestDTO.getName());
        digitalSignature.setDocumentContent(attachmentRequestDTO.getArchive());
        digitalSignature.setTimestamp(LocalDateTime.now());
        digitalSignature.setValidFrom(digitalSignatureRequestDTO.validFrom());
        digitalSignature.setValidUntil(digitalSignatureRequestDTO.validUntil());
        digitalSignature.setActive(true);
        digitalSignature.setUser(user);
        digitalSignature.setSignature(signature);

        user.setPublicKey(keyPair.getPublic());
        userRepository.save(user);

        return digitalSignatureMapperDTOService.toDTO(digitalSignatureRepository.save(digitalSignature));
    }

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate key pair", e);
        }
    }

    private byte[] signDocument(byte[] documentContent, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(documentContent);
            return signature.sign();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Failed to sign document", e);
        }
    }
}
