package com.inovamed.clinical_study_system.model.digital_signature;

import java.time.LocalDateTime;
import java.util.List;

public record DigitalSignatureRequestDTO(Long userId,
                                         List<Long> consentsId, LocalDateTime validFrom,
                                         LocalDateTime validUntil) {


}
