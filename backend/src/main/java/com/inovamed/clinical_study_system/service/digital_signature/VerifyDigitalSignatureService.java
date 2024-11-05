package com.inovamed.clinical_study_system.service.digital_signature;

import com.inovamed.clinical_study_system.exception.SignatureErrorVerifyException;
import com.inovamed.clinical_study_system.exception.SignatureNotFoundException;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.repository.DigitalSignatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.security.Signature;

@Service
public class VerifyDigitalSignatureService {
    @Autowired
    private DigitalSignatureRepository digitalSignatureRepository;

    public boolean execute(Long signatureId){

            DigitalSignature digitalSignature = digitalSignatureRepository.findById(signatureId)
                    .orElseThrow(()->{
                        return new SignatureNotFoundException();
                    });
        try{
            PublicKey publicKey = digitalSignature.getUser().getPublicKey();

            return verifyDocumentSignature(digitalSignature.getDocumentContent(),digitalSignature.getSignature(), publicKey);
        } catch (Exception e) {
            throw new SignatureErrorVerifyException(e);
        }
    }
    protected boolean verifyDocumentSignature(byte[] documentContent, byte[] signature, PublicKey publicKey) throws Exception{
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(documentContent);
        return sig.verify(signature);
    }
}
