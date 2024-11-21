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

    // Usando @Lob corretamente com @Basic(fetch = FetchType.LAZY)
    @Lob
    @Basic(fetch = FetchType.LAZY)  // Carregamento sob demanda
    private byte[] documentContent;

    @Lob
    @Basic(fetch = FetchType.LAZY)  // Carregamento sob demanda
    private byte[] signature;

    @ManyToMany
    @JoinTable(
            name = "signature_consents",
            joinColumns = @JoinColumn(name = "signature_id"),
            inverseJoinColumns = @JoinColumn(name = "consent_id")
    )
    private List<Consent> consents;

    private LocalDateTime timestamp;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private boolean isActive;

    @OneToOne
    private User user;

    // Método para desativar a assinatura se estiver expirada
    public void deactivateIfExpired() {
        if (LocalDateTime.now().isAfter(validUntil)) {
            this.isActive = false;
        }
    }
}
