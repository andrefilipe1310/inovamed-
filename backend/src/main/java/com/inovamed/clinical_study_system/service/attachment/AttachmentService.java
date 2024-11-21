package com.inovamed.clinical_study_system.service.attachment;

import com.inovamed.clinical_study_system.exception.AttachmentNotFoundException;
import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.model.attachment.Attachment;
import com.inovamed.clinical_study_system.model.attachment.AttachmentCreateResponseDTO;
import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.attachment.AttachmentFindResponseDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.repository.AttachmentRepository;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttachmentService implements IAttachmentService{
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private ClinicalStudyRepresentiveRepository clinicalRepository;

    @Override
    public AttachmentCreateResponseDTO upload(AttachmentRequestDTO attachmentRequestDTO, Long userId) {
        ClinicalStudyRepresentative clinicalRepresentative = this.clinicalRepository.findById(userId).orElseThrow(
                () -> {return new ClinicalRepresentativeNotFoundException();}
        );

        attachmentRequestDTO.setName(attachmentRequestDTO.getName() + " " + clinicalRepresentative.getName() + " " + clinicalRepresentative.getId());
        Attachment attachment = this.toEntity(attachmentRequestDTO);

        return this.toCreateResponseDTO(attachmentRepository.save(attachment));
    }

    @Override

    public List<AttachmentFindResponseDTO> findAll() {
        return attachmentRepository.findAll().stream().map(attachment -> {
            return this.toFindResponseDTO(attachment,false);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AttachmentFindResponseDTO findById(Long id) {
        return this.toFindResponseDTO(attachmentRepository.findById(id).orElseThrow(
                ()->{return new AttachmentNotFoundException();}
        ),true);
    }

    @Override
    @Transactional
    public List<AttachmentFindResponseDTO> findAllById(Long id) {
        return attachmentRepository.findAllByUserId(id).stream().map(attachment -> {
            return this.toFindResponseDTO(attachment,true);
        }).collect(Collectors.toList());
    }

    @Override
    public AttachmentFindResponseDTO update(Long id, AttachmentRequestDTO attachmentRequestDTO) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(
                ()->{return new AttachmentNotFoundException();}
        );

        attachment.update(attachmentRequestDTO);
        AttachmentFindResponseDTO updatedAttachment = this.toFindResponseDTO(attachmentRepository.save(attachment),true);

        return updatedAttachment;
    }

    @Override
    public String delete(Long id) {
        if (!attachmentRepository.existsById(id)){
            throw new AttachmentNotFoundException();
        }
        this.attachmentRepository.deleteById(id);

        return "attachment "+id+" deleted success.";
    }

    private AttachmentCreateResponseDTO toCreateResponseDTO(Attachment attachment){
        return new AttachmentCreateResponseDTO(attachment.getName(),"file saved successfully");
    }

    private AttachmentFindResponseDTO toFindResponseDTO(Attachment attachment,boolean showFile){
        if (!showFile){
            return new AttachmentFindResponseDTO(attachment.getName(),null);
        }
        return new AttachmentFindResponseDTO(attachment.getName(),attachment.getArchive());

    }
    private Attachment toEntity(AttachmentRequestDTO attachmentRequestDTO){

        ClinicalStudyRepresentative clinicalRepresentative = clinicalRepository
                .findById(attachmentRequestDTO.getUserId())
                .orElseThrow(()->{
                    return new ClinicalRepresentativeNotFoundException();
                });
        Attachment attachment = new Attachment();
        attachment.setName(attachmentRequestDTO.getName());
        attachment.setArchive(attachmentRequestDTO.getArchive());
        attachment.setUser(clinicalRepresentative);
        return attachment;
    }
}
