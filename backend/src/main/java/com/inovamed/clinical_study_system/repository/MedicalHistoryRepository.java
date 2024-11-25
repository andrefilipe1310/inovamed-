package com.inovamed.clinical_study_system.repository;

import com.inovamed.clinical_study_system.model.medical_history.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository  extends JpaRepository<MedicalHistory,Long> {
    MedicalHistory findByPatientId(Long patientId);
    MedicalHistory findByPatientCode(String patientCode);
}
