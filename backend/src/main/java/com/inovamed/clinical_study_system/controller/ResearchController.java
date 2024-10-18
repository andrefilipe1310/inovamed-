package com.inovamed.clinical_study_system.controller;

import com.inovamed.clinical_study_system.model.research.ResearchRequestDTO;
import com.inovamed.clinical_study_system.model.research.ResearchResponseDTO;
import com.inovamed.clinical_study_system.service.research.CreateResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/research")
public class ResearchController {
    @Autowired
    CreateResearchService createResearchService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<ResearchResponseDTO> create(@RequestBody ResearchRequestDTO researchRequestDTO){
        System.out.println(researchRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createResearchService.execute(researchRequestDTO));
    }

}
