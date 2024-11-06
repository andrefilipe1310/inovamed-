package com.inovamed.clinical_study_system.repository;


import com.inovamed.clinical_study_system.model.research.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResearchRepository extends JpaRepository<Research,Long> {
    public List<Research> findAllByClinicalRepresentativeId(Long clinicalRepresentativeId);
    public Optional<Research> findByCode(int code);
}
