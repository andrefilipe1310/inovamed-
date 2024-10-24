package com.inovamed.clinical_study_system.controller;

import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignature;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureRequestDTO;
import com.inovamed.clinical_study_system.service.digital_signature.CreateDigitalSignatureService;
import com.inovamed.clinical_study_system.service.digital_signature.DeactivateDigitalSignatureService;
import com.inovamed.clinical_study_system.service.digital_signature.VerifyDigitalSignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/digital-signature")
public class DigitalSignatureController {
    @Autowired
    private CreateDigitalSignatureService createDigitalSignatureService;
    @Autowired
    private VerifyDigitalSignatureService verifyDigitalSignatureService;
    @Autowired
    private DeactivateDigitalSignatureService deactivateDigitalSignatureService;

    @PostMapping
    public ResponseEntity<DigitalSignature> create(@ModelAttribute  DigitalSignatureRequestDTO digitalSignatureRequestDTO, @RequestParam("file") MultipartFile file){
        try{
            AttachmentRequestDTO attachmentRequestDTO = new AttachmentRequestDTO(file.getName(), file.getBytes());
            return ResponseEntity.status(HttpStatus.CREATED).body(createDigitalSignatureService.execute(digitalSignatureRequestDTO, attachmentRequestDTO));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/verify/{id}")
    public ResponseEntity<Boolean> verify(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(verifyDigitalSignatureService.execute(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deactivate(@PathVariable("id") Long id){
        this.deactivateDigitalSignatureService.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
