package com.inovamed.clinical_study_system.controller;


import com.inovamed.clinical_study_system.model.application.ApplicationRequestDTO;
import com.inovamed.clinical_study_system.model.application.ApplicationResponseDTO;
import com.inovamed.clinical_study_system.service.application.CreateApplicationService;
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
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    CreateApplicationService createApplicationService;
    @Autowired
    TokenService tokenService;


    @PostMapping
    public ResponseEntity<ApplicationResponseDTO> create(HttpServletRequest request, @RequestBody ApplicationRequestDTO applicationRequestDTO){
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(createApplicationService.execute(applicationRequestDTO,userId));
    }


}
