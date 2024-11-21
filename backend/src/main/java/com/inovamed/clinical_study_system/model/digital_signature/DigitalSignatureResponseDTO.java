package com.inovamed.clinical_study_system.model.digital_signature;

import java.time.LocalDateTime;

public record DigitalSignatureResponseDTO (String documentName,
                                           byte[] signature,
                                           LocalDateTime validFrom,LocalDateTime validUntil,boolean isActive){
}
