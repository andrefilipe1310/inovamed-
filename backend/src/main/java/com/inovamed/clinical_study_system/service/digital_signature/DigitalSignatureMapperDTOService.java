package com.inovamed.clinical_study_system.service.digital_signature;

import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureResponseDTO;
import org.springframework.stereotype.Service;



@Service
public class DigitalSignatureMapperDTOService {


    public DigitalSignatureResponseDTO toDTO(DigitalSignature digitalSignature){

        return new DigitalSignatureResponseDTO(
                digitalSignature.getDocumentName(),
                digitalSignature.getSignature(),
                digitalSignature.getValidFrom(),
                digitalSignature.getValidUntil(),
                digitalSignature.isActive()
        );

    }
}
