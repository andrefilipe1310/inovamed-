package com.inovamed.clinical_study_system.controller;

import com.inovamed.clinical_study_system.model.doctor.DoctorRequestDTO;
import com.inovamed.clinical_study_system.model.doctor.DoctorResponseDTO;
import com.inovamed.clinical_study_system.model.doctor.DoctorUpdateDTO;
import com.inovamed.clinical_study_system.service.doctor.*;
import com.inovamed.clinical_study_system.service.token.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private CreateDoctorService createDoctorService;
    @Autowired
    private FindAllDoctorService findAllDoctorService;
    @Autowired
    private FindByIdDoctorService findByIdDoctorService;
    @Autowired
    private UpdateDoctorService updateDoctorService;
    @Autowired
    private DeleteDoctorService deleteDoctorService;
    @Autowired
    TokenService tokenService;


    @PostMapping
    public ResponseEntity<DoctorResponseDTO> create(@RequestBody DoctorRequestDTO doctorRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createDoctorService.execute(doctorRequestDTO));
    }

    @GetMapping
    public ResponseEntity<DoctorResponseDTO> findAll(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(findAllDoctorService.execute(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO > findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(findByIdDoctorService.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO>  update(@PathVariable("id") Long id, @RequestBody DoctorUpdateDTO DoctorUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(updateDoctorService.execute(id, DoctorUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deleteDoctorService.execute(id));
    }

}
