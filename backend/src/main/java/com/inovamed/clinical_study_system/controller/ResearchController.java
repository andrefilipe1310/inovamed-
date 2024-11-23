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
    FindAllByUserIdResearchService findAllByUserIdResearchService;
    @Autowired
    AddRepresentativeInResearchService addRepresentativeInResearchService;
    @Autowired
    FindAllFeaturesByUserIdResearchService findAllFeaturesByUserId;
    @Autowired
    FindByCodeResearchService findByCodeResearchService;
    @Autowired
    UpdateResearchByIdService updateResearchByIdService;
    @Autowired
    FindAllFeaturesResearchService findAllFeaturesResearchService;
    @Autowired
    TokenService tokenService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<ResearchResponseDTO> create(
            HttpServletRequest request,
            @ModelAttribute ResearchRequestDTO researchRequestDTO,
            @RequestParam("file") List<MultipartFile> file) throws IOException {
        System.out.println("Received DTO: " + researchRequestDTO);
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);

        // Agora passe phases convertida para o serviço de criação
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createResearchService.execute(researchRequestDTO, file, userId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<ResearchResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.findAllResearchService.execute());
    }

    @GetMapping
    public ResponseEntity<List<ResearchResponseDTO>> findAllByUserId(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(this.findAllByUserIdResearchService.execute(userId));
    }
    @GetMapping("/code/{code}")
    public ResponseEntity<ResearchResponseDTO> findByCode(@PathVariable("code") int code){
        return ResponseEntity.status(HttpStatus.OK).body(this.findByCodeResearchService.execute(code));
    }

    @GetMapping("/feature")
    public ResponseEntity<List<ResearchFeaturesResponseDTO>> findAllFeaturesByUserId(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(this.findAllFeaturesByUserId.execute(userId));
    }
    @GetMapping("/feature-all")
    public ResponseEntity<List<ResearchFeaturesResponseDTO>> findAllFeatures(){
        return ResponseEntity.status(HttpStatus.OK).body(this.findAllFeaturesResearchService.execute());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResearchResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.findByIdResearchService.execute(id));
    }
    @PutMapping("{id}")
    public ResponseEntity<ResearchResponseDTO> updateById(@PathVariable("id") Long id, @RequestBody ResearchUpdateDTO researchUpdateDTO){

        return ResponseEntity.status(HttpStatus.OK).body(this.updateResearchByIdService.execute(id,researchUpdateDTO));
    }
    @PutMapping("code/{code}")
    public ResponseEntity<ResearchResponseDTO> update(HttpServletRequest request,
                                                      @RequestBody ResearchUpdateDTO researchUpdateDTO,@PathVariable("code") int code){
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(this.updateResearchService.execute(userId,researchUpdateDTO,code));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        this.deleteByIdResearshService.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/add-clinical-representative")
    public ResponseEntity<ResearchResponseDTO> addClinicalRepresentative(@RequestBody ResearchAddRepresentativeDTO researchAddRepresentativeDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.addRepresentativeInResearchService.execute(researchAddRepresentativeDTO));
    }

}
