package com.inovamed.clinical_study_system.controller;

import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeRequestDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeUpdateDTO;


import com.inovamed.clinical_study_system.service.clinical_study_representative.*;
import com.inovamed.clinical_study_system.service.token.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private TokenService tokenService;
 



    @PostMapping
    public ResponseEntity<ClinicalStudyRepresentativeResponseDTO> create(@RequestBody ClinicalStudyRepresentativeRequestDTO clinicalRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createClinicalRepresentativeService.execute(clinicalRequestDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClinicalStudyRepresentativeResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(findAllClinicalRepresentativeService.execute());
    }

    @GetMapping
    public ResponseEntity<ClinicalStudyRepresentativeResponseDTO> findById(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(findByIdClinicalRepresentativeService.execute(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalStudyRepresentativeResponseDTO> update(@PathVariable("id") Long id, @RequestBody ClinicalStudyRepresentativeUpdateDTO clinicalStudyRepresentativeUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(updateClinicalRepresentativeService.execute(id, clinicalStudyRepresentativeUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        deleteByIdClinicalRepresentativeService.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

