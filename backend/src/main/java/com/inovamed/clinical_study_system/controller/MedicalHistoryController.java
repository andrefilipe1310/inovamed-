package com.inovamed.clinical_study_system.controller;


import com.inovamed.clinical_study_system.model.medical_history.MedicalHistoryRequestDTO;
import com.inovamed.clinical_study_system.model.medical_history.MedicalHistoryResponseDTO;
import com.inovamed.clinical_study_system.service.medical_history.CreateMedicalHistoryByPatientIdService;
import com.inovamed.clinical_study_system.service.token.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical-history")
public class MedicalHistoryController {

    @Autowired
    CreateMedicalHistoryByPatientIdService createMedicalHistoryByPatientIdService;
    @Autowired
    TokenService tokenService;


    @PostMapping
    public ResponseEntity<MedicalHistoryResponseDTO> create(@RequestBody MedicalHistoryRequestDTO medicalHistoryRequestDTO, HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);

        return ResponseEntity.status(HttpStatus.CREATED).body(createMedicalHistoryByPatientIdService.execute(medicalHistoryRequestDTO,userId));

    }
}
