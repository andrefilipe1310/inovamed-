package com.inovamed.clinical_study_system.controller;


import com.inovamed.clinical_study_system.model.doctor.DoctorRequestDTO;
import com.inovamed.clinical_study_system.model.doctor.DoctorResponseDTO;
import com.inovamed.clinical_study_system.model.patient.PatientRequestDTO;
import com.inovamed.clinical_study_system.model.patient.PatientResponseDTO;
import com.inovamed.clinical_study_system.service.patient.CreatePatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private CreatePatientService createPatientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@RequestBody PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createPatientService.execute(patientRequestDTO));
    }
}
