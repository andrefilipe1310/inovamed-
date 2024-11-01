package com.inovamed.clinical_study_system.model.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentRequestDTO {

    String name;
    byte[] archive;
    Long userId;
}
