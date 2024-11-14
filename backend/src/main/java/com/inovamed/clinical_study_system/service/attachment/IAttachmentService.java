package com.inovamed.clinical_study_system.service.attachment;

import com.inovamed.clinical_study_system.model.attachment.AttachmentCreateResponseDTO;
import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.attachment.AttachmentFindResponseDTO;


import java.util.List;

public interface IAttachmentService {
    AttachmentCreateResponseDTO upload(AttachmentRequestDTO attachmentRequestDTO,Long userId);
    List<AttachmentFindResponseDTO> findAll();
    AttachmentFindResponseDTO findById(Long id);
    List<AttachmentFindResponseDTO> findAllById(Long id);
    AttachmentFindResponseDTO update(Long id, AttachmentRequestDTO attachmentRequestDTO);
    String delete(Long id);
}
