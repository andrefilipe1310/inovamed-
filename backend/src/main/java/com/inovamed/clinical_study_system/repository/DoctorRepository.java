package com.inovamed.clinical_study_system.repository;

import com.inovamed.clinical_study_system.model.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
