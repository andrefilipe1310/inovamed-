package com.inovamed.clinical_study_system.controller;


import com.inovamed.clinical_study_system.model.application.ApplicationPatientRequestDTO;
import com.inovamed.clinical_study_system.model.application.ApplicationPatientResponseDTO;
import com.inovamed.clinical_study_system.model.application.ApplicationRequestDTO;
import com.inovamed.clinical_study_system.model.application.ApplicationResponseDTO;
import com.inovamed.clinical_study_system.service.application.CreateApplicationService;
import com.inovamed.clinical_study_system.service.application.FindAllApplicationService;
import com.inovamed.clinical_study_system.service.application.FindAllApplicationsByResearchCodeService;
import com.inovamed.clinical_study_system.service.token.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    CreateApplicationService createApplicationService;
    @Autowired
    FindAllApplicationService findAllApplicationService;
    @Autowired
    FindAllApplicationsByResearchCodeService findAllApplicationsByResearchCodeService;
    @Autowired
    TokenService tokenService;


    @PostMapping
    public ResponseEntity<ApplicationResponseDTO> create(HttpServletRequest request, @RequestBody ApplicationRequestDTO applicationRequestDTO){
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(createApplicationService.execute(applicationRequestDTO,userId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<ApplicationResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(findAllApplicationService.execute());
    }
    @GetMapping("/research/{code}")
    public ResponseEntity<List<ApplicationPatientResponseDTO>> findByResearchId(@PathVariable("code") int code,HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);
        ApplicationPatientRequestDTO applicationPatientRequestDTO = new ApplicationPatientRequestDTO(code);
        return ResponseEntity.status(HttpStatus.OK).body(findAllApplicationsByResearchCodeService.execute(applicationPatientRequestDTO,userId));

    }


}
