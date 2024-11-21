package com.inovamed.clinical_study_system.service.digital_signature;


import com.inovamed.clinical_study_system.exception.FailedToSignDocumentException;
import com.inovamed.clinical_study_system.exception.InvalidSignatureValidityException;
import com.inovamed.clinical_study_system.exception.PatientNotFoundException;
import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureRequestDTO;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureResponseDTO;
import com.inovamed.clinical_study_system.model.patient.Patient;
import com.inovamed.clinical_study_system.repository.DigitalSignatureRepository;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private PatientRepository patientRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreateDigitalSignatureService.class);

    @Transactional
    public DigitalSignatureResponseDTO execute(DigitalSignatureRequestDTO digitalSignatureRequestDTO, AttachmentRequestDTO attachmentRequestDTO,Long userId) throws NoSuchAlgorithmException {
        if(digitalSignatureRepository.existsByUserId(userId)){
            throw new RuntimeException("assinatura já feita");
        }

        if (digitalSignatureRequestDTO.validFrom().isAfter(digitalSignatureRequestDTO.validUntil())) {
            throw new InvalidSignatureValidityException();
        }


        Patient patient = patientRepository.findById(userId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));

        logger.info("Criando assinatura digital para o usuário: {}", patient.getId());
        KeyPair keyPair = generateKeyPair();
        byte[] signature = signDocument(attachmentRequestDTO.getArchive(), keyPair.getPrivate());
        DigitalSignature digitalSignature = new DigitalSignature();
        digitalSignature.setDocumentName(attachmentRequestDTO.getName());
        digitalSignature.setDocumentContent(attachmentRequestDTO.getArchive());
        digitalSignature.setTimestamp(LocalDateTime.now());
        digitalSignature.setValidUntil(digitalSignatureRequestDTO.validUntil());
        digitalSignature.setValidFrom(digitalSignatureRequestDTO.validFrom());
        digitalSignature.setActive(true);
        digitalSignature.setUser(patient);
        digitalSignature.setSignature(signature);
        patient.setPublicKey(keyPair.getPublic());
        patientRepository.save(patient);

        logger.info("Assinatura digital criada com sucesso para o usuário: {}", patient.getId());

        return digitalSignatureMapperDTOService.toDTO(digitalSignatureRepository.save(digitalSignature));
    }

    // Gerar assinatura
    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            return keyPairGenerator.generateKeyPair();
    }
    @Transactional
    private byte[] signDocument(byte[] documentContent, PrivateKey privateKey)  {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(documentContent);
            return signature.sign();
        } catch (GeneralSecurityException e) {
            throw new FailedToSignDocumentException();
        }
    }
}
