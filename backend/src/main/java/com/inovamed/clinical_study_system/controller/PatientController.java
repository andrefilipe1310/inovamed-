package com.inovamed.clinical_study_system.controller;


import com.inovamed.clinical_study_system.model.patient.PatientRequestDTO;
import com.inovamed.clinical_study_system.model.patient.PatientResponseDTO;
import com.inovamed.clinical_study_system.model.patient.PatientUpdateDTO;
import com.inovamed.clinical_study_system.service.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private CreatePatientService createPatientService;
    @Autowired
    FindAllPatientsService findAllPatientsService;
    @Autowired
    FindByIdPatientService findByIdPatientService;
    @Autowired
    UpdatePatientService updatePatientService;
    @Autowired
    DeleteByIdPatientService deleteByIdPatientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@RequestBody PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createPatientService.execute(patientRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(findAllPatientsService.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(findByIdPatientService.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(@PathVariable("id") Long id,@RequestBody PatientUpdateDTO patientUpdateDTO){
        return ResponseEntity.status(HttpStatus.OK).body(updatePatientService.execute(id,patientUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        deleteByIdPatientService.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
