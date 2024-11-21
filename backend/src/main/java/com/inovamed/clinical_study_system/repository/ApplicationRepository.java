package com.inovamed.clinical_study_system.repository;

import com.inovamed.clinical_study_system.model.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository  extends JpaRepository<Application,Long> {
    List<Application> findByResearchCode(int code);
}
