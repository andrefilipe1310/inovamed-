package com.inovamed.clinical_study_system.service.digital_signature;

import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureRequestDTO;
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
    DigitalSignatureRepository digitalSignatureRepository;
    @Autowired
    UserRepository userRepository;

    public DigitalSignature execute(DigitalSignatureRequestDTO digitalSignatureRequestDTO, AttachmentRequestDTO attachmentRequestDTO){
        try {

            User user = userRepository.findById(digitalSignatureRequestDTO.userId()).orElseThrow(
                    ()->{
                        return new RuntimeException("User not found.");
                    }
            );
            // Gerar o par de chaves
            KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();



            //Assinar o documento
            byte[] signature = signDocument(attachmentRequestDTO.archive(),privateKey);

            DigitalSignature digitalSignature = new DigitalSignature();
            digitalSignature.setDocumentName(attachmentRequestDTO.name());
            digitalSignature.setDocumentContent(attachmentRequestDTO.archive());
            digitalSignature.setTimestamp(LocalDateTime.now());
            digitalSignature.setValidFrom(digitalSignatureRequestDTO.validFrom());
            digitalSignature.setValidUntil(digitalSignatureRequestDTO.validUntil());
            digitalSignature.setActive(true);
            digitalSignature.setUser(user);
            digitalSignature.setSignature(signature);

            user.setPublicKey(publicKey);
            userRepository.save(user);

            return digitalSignatureRepository.save(digitalSignature);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }


    }

    private byte[] signDocument(byte[] documentContent, PrivateKey privateKey) throws Exception{
        // Classe do javaSecurity
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(documentContent);
        return signature.sign();
    }
}
