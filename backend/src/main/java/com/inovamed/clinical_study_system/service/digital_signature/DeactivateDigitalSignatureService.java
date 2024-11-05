package com.inovamed.clinical_study_system.service.digital_signature;

import com.inovamed.clinical_study_system.exception.SignatureIsInactiveException;
import com.inovamed.clinical_study_system.exception.SignatureNotFoundException;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.repository.DigitalSignatureRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeactivateDigitalSignatureService {
    @Autowired
    private DigitalSignatureRepository digitalSignatureRepository;


    @Transactional
    public void execute(Long signatureId){
        DigitalSignature digitalSignature = digitalSignatureRepository.findById(signatureId).orElseThrow(
                ()->{
                    return new SignatureNotFoundException();
                }
        );

        if (!digitalSignature.isActive()){
            throw new SignatureIsInactiveException();
        }
        digitalSignature.setActive(false);
        digitalSignatureRepository.save(digitalSignature);
    }

}
