package com.inovamed.clinical_study_system.repository;

import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    UserDetails findByEmail(String email);
    Optional<Patient> findByCode(String code);
}
