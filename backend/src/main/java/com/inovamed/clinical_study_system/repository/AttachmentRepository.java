package com.inovamed.clinical_study_system.repository;

import com.inovamed.clinical_study_system.model.attachment.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
    List<Attachment> findAllByUserId(Long id);
}
