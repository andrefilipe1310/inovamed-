package com.inovamed.clinical_study_system.model.attachment;

import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.research.Research;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_attachment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private byte[] archive;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private Notification notification;

    @ManyToOne
    @JoinColumn(name = "research_id")
    private Research research;

    public void update(AttachmentRequestDTO attachmentRequestDTO){
        updateField(() -> this.name = attachmentRequestDTO.name(), attachmentRequestDTO.name());
        updateField(() -> this.archive = attachmentRequestDTO.archive(), attachmentRequestDTO.archive());
    }

    private <T> void updateField(Runnable updateAction, T newValue) {
        if (newValue != null) {
            updateAction.run();
        }
    }

}
