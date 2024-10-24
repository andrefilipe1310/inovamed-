package com.inovamed.clinical_study_system.controller;


import com.inovamed.clinical_study_system.model.research.ResearchAddRepresentativeDTO;
import com.inovamed.clinical_study_system.model.research.ResearchRequestDTO;
import com.inovamed.clinical_study_system.model.research.ResearchResponseDTO;
import com.inovamed.clinical_study_system.model.research.ResearchUpdateDTO;
import com.inovamed.clinical_study_system.service.research.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/research")
public class ResearchController {
    @Autowired
    CreateResearchService createResearchService;
    @Autowired
    FindAllResearchService findAllResearchService;
    @Autowired
    FindByIdResearchService findByIdResearchService;
    @Autowired
    UpdateResearchService updateResearchService;
    @Autowired
    DeleteByIdResearshService deleteByIdResearshService;
    @Autowired
    AddRepresentativeInResearchService addRepresentativeInResearchService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<ResearchResponseDTO> create(@RequestBody ResearchRequestDTO researchRequestDTO){
        System.out.println(researchRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createResearchService.execute(researchRequestDTO));
    }
    @GetMapping
    public ResponseEntity<List<ResearchResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.findAllResearchService.execute());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResearchResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.findByIdResearchService.execute(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResearchResponseDTO> update(@PathVariable("id") Long id,
                                                      @RequestBody ResearchUpdateDTO researchUpdateDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.updateResearchService.execute(id,researchUpdateDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        this.deleteByIdResearshService.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/add-clinical-representative")
    public ResponseEntity<ResearchResponseDTO> addClinicalRepresentative(@RequestBody ResearchAddRepresentativeDTO researchAddRepresentativeDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.addRepresentativeInResearchService.execute(researchAddRepresentativeDTO));
    }

}
