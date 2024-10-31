package com.inovamed.clinical_study_system.model.consent;


import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.model.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "tb_consent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ConsentType consentType;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private boolean isActive;

//    @ManyToMany
//    @JoinTable(
//            name = "tb_consent_digital_signature",
//            joinColumns = @JoinColumn(name = "consent_id"),
//            inverseJoinColumns = @JoinColumn(name = "digital_signature_id")
//    )
//    private List<DigitalSignature> digitalSignature;
}
