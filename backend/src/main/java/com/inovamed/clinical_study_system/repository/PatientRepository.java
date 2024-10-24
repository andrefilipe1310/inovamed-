package com.inovamed.clinical_study_system.repository;

import com.inovamed.clinical_study_system.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
