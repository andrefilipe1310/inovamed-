package com.inovamed.clinical_study_system.controller;

import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentativeResponseDTO;
import com.inovamed.clinical_study_system.service.clinical_study_representative.CreateClinicalRepresentativeService;
import com.inovamed.clinical_study_system.service.clinical_study_representative.FindAllClinicalRepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("clinical_representative")
public class ClinicalRepresentativeController {
    @Autowired
    private CreateClinicalRepresentativeService createClinicalRepresentativeService;
    @Autowired
    private FindAllClinicalRepresentativeService findAllClinicalRepresentativeService;


    public ResponseEntity<List<ClinicalStudyRepresentativeResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(findAllClinicalRepresentativeService.execute());
    }
}
