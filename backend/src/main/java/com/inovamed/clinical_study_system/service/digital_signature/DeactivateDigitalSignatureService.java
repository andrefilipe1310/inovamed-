package com.inovamed.clinical_study_system.service.digital_signature;

import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.repository.DigitalSignatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeactivateDigitalSignatureService {
    @Autowired
    private DigitalSignatureRepository digitalSignatureRepository;

    public void execute(Long signatureId){
        DigitalSignature digitalSignature = digitalSignatureRepository.findById(signatureId).orElseThrow(
                ()->{
                    return new RuntimeException("Signature not found.");
                }
        );

        if (!digitalSignature.isActive()){
            throw new RuntimeException("Signature is already inactive.");
        }
        digitalSignature.setActive(false);
        digitalSignatureRepository.save(digitalSignature);
    }

}
