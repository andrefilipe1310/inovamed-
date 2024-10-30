package com.inovamed.clinical_study_system.controller;

import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeRequestDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.model.user.User;
import com.inovamed.clinical_study_system.repository.UserRepository;
import com.inovamed.clinical_study_system.service.clinical_study_representative.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinical-representative")
public class ClinicalRepresentativeController {
    @Autowired
    private CreateClinicalRepresentativeService createClinicalRepresentativeService;
    @Autowired
    private FindAllClinicalRepresentativeService findAllClinicalRepresentativeService;
    @Autowired
    private FindByIdClinicalRepresentativeService findByIdClinicalRepresentativeService;
    @Autowired
    private DeleteByIdClinicalRepresentativeService deleteByIdClinicalRepresentativeService;
    @Autowired
    private UpdateClinicalRepresentativeService updateClinicalRepresentativeService;
    @Autowired
    private UserRepository userRepository;



    @PostMapping
    public ResponseEntity<ClinicalStudyRepresentativeResponseDTO> create(@RequestBody ClinicalStudyRepresentativeRequestDTO clinicalRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createClinicalRepresentativeService.execute(clinicalRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ClinicalStudyRepresentativeResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(findAllClinicalRepresentativeService.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalStudyRepresentativeResponseDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(findByIdClinicalRepresentativeService.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalStudyRepresentativeResponseDTO> update(@PathVariable("id") Long id, @RequestBody ClinicalStudyRepresentativeRequestDTO clinicalStudyRepresentativeRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(updateClinicalRepresentativeService.execute(id, clinicalStudyRepresentativeRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        deleteByIdClinicalRepresentativeService.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

