package com.inovamed.clinical_study_system.controller;


import com.inovamed.clinical_study_system.model.notification.NotificationResponseDTO;
import com.inovamed.clinical_study_system.model.notification.NotificationResquestDTO;
import com.inovamed.clinical_study_system.service.notification.CreateNotificationService;
import com.inovamed.clinical_study_system.service.notification.DeleteNotificationService;
import com.inovamed.clinical_study_system.service.notification.FindAllNotificationService;
import com.inovamed.clinical_study_system.service.notification.FindByIdNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<NotificationResponseDTO> create(@RequestBody NotificationResquestDTO notificationResquestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(createNotificationService.execute(notificationResquestDTO));
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(findAllNotificationService.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(findByIdNotificationService.execute(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        deleteNotificationService.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
