package com.inovamed.clinical_study_system.model.digital_signature;

import com.inovamed.clinical_study_system.model.user.User;

import java.time.LocalDateTime;
import java.util.List;

public record DigitalSignatureRequestDTO(Long userId, byte[] documentContent,
                                         List<Long> consentsId, LocalDateTime validFrom,
                                         LocalDateTime validUntil) {

}
