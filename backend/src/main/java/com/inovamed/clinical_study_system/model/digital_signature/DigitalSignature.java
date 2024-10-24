package com.inovamed.clinical_study_system.model.digital_signature;


import com.inovamed.clinical_study_system.model.consent.Consent;
import com.inovamed.clinical_study_system.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "tb_digital_signature")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DigitalSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentName;
    @Lob
    private byte[] documentContent;
    @Lob
    private byte[] signature;
    //@ManyToMany
    //List<Consent> consents;
    private LocalDateTime timestamp;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private boolean isActive;
    @OneToOne
    private User user;


}
