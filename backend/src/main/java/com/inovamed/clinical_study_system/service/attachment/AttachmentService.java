package com.inovamed.clinical_study_system.service.attachment;

import com.inovamed.clinical_study_system.model.attachment.Attachment;
import com.inovamed.clinical_study_system.model.attachment.AttachmentCreateResponseDTO;
import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.attachment.AttachmentFindResponseDTO;
import com.inovamed.clinical_study_system.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttachmentService implements IAttachmentService{
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public AttachmentCreateResponseDTO upload(AttachmentRequestDTO attachmentRequestDTO) {
        Attachment attachment = this.toEntity(attachmentRequestDTO);

        return this.toCreateResponseDTO(attachmentRepository.save(attachment));
    }

    @Override
    public List<AttachmentFindResponseDTO> findAll() {
        return attachmentRepository.findAll().stream().map(attachment -> {
            return this.toFindResponseDTO(attachment);
        }).collect(Collectors.toList());
    }

    @Override
    public AttachmentFindResponseDTO findById(Long id) {
        return this.toFindResponseDTO(attachmentRepository.findById(id).orElseThrow(
                ()->{return new RuntimeException("Attachment not found.");}
        ));
    }

    @Override
    public AttachmentFindResponseDTO update(Long id, AttachmentRequestDTO attachmentRequestDTO) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(
                ()->{return new RuntimeException("Attachment not found.");}
        );

        attachment.update(attachmentRequestDTO);
        return this.toFindResponseDTO(attachmentRepository.save(attachment));
    }

    @Override
    public String delete(Long id) {
        this.attachmentRepository.deleteById(id);
        if(attachmentRepository.existsById(id)){
            throw new RuntimeException("attachment not deleted.");
        }
        return "attachment "+id+" deleted success.";
    }

    private AttachmentCreateResponseDTO toCreateResponseDTO(Attachment attachment){
        return new AttachmentCreateResponseDTO(attachment.getName());
    }
    private AttachmentFindResponseDTO toFindResponseDTO(Attachment attachment){
        return new AttachmentFindResponseDTO(attachment.getName(),attachment.getArchive());
    }
    private Attachment toEntity(AttachmentRequestDTO attachmentRequestDTO){
        Attachment attachment = new Attachment();
        attachment.setName(attachmentRequestDTO.name());
        attachment.setArchive(attachmentRequestDTO.archive());
        return attachment;
    }
}
