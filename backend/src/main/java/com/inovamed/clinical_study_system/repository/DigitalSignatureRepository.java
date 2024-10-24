package com.inovamed.clinical_study_system.repository;


import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalSignatureRepository extends JpaRepository<DigitalSignature,Long> {
}
