package com.inovamed.clinical_study_system.controller;


import com.inovamed.clinical_study_system.model.notification.NotificationResponseDTO;
import com.inovamed.clinical_study_system.model.notification.NotificationResquestDTO;
import com.inovamed.clinical_study_system.service.notification.CreateNotificationService;
import com.inovamed.clinical_study_system.service.notification.DeleteNotificationService;
import com.inovamed.clinical_study_system.service.notification.FindAllNotificationService;
import com.inovamed.clinical_study_system.service.notification.FindByIdNotificationService;
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
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private CreateNotificationService createNotificationService;
    @Autowired
    private FindAllNotificationService findAllNotificationService;
    @Autowired
    private FindByIdNotificationService findByIdNotificationService;
    @Autowired
    private DeleteNotificationService deleteNotificationService;
    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<NotificationResponseDTO> create(HttpServletRequest request,@RequestParam("file") List<MultipartFile> files, @ModelAttribute NotificationResquestDTO notificationResquestDTO) throws IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);

        return ResponseEntity.status(HttpStatus.CREATED).body(createNotificationService.execute(notificationResquestDTO,files,userId));
    }

    @GetMapping("/patient")
    public ResponseEntity<List<NotificationResponseDTO>> findAllByPatient(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(findAllNotificationService.execute(userId,"PATIENT"));
    }
    @GetMapping("/doctor")
    public ResponseEntity<List<NotificationResponseDTO>> findAllByDoctor(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(findAllNotificationService.execute(userId,"DOCTOR"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(findByIdNotificationService.execute(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        deleteNotificationService.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
