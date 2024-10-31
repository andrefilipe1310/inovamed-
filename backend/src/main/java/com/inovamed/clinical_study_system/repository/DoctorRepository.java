package com.inovamed.clinical_study_system.repository;

import com.inovamed.clinical_study_system.model.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByKey(String key);
    UserDetails findByEmail(String email);
}
