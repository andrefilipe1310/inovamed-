package com.inovamed.clinical_study_system.repository;

import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicalStudyRepresentiveRepository extends JpaRepository<ClinicalStudyRepresentative,Long> {
 UserDetails findByEmail(String email);

}




