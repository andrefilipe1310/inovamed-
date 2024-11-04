package com.inovamed.clinical_study_system.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inovamed.clinical_study_system.model.research.*;
import com.inovamed.clinical_study_system.service.research.*;
import com.inovamed.clinical_study_system.service.token.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Autowired
    TokenService tokenService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<ResearchResponseDTO> create(
            HttpServletRequest request,
            @ModelAttribute ResearchRequestDTO researchRequestDTO,
            @RequestParam("file") MultipartFile file) throws IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);

        // Converter phases de JSON para List<Phases>
        ObjectMapper objectMapper = new ObjectMapper();
        List<Phases> phases = objectMapper.readValue(researchRequestDTO.phases(), new TypeReference<List<Phases>>() {});

        // Agora passe phases convertida para o serviço de criação, por exemplo
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createResearchService.execute(researchRequestDTO, file, userId, phases));
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
