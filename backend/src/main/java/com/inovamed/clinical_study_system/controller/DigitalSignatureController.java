package com.inovamed.clinical_study_system.controller;


import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureRequestDTO;
import com.inovamed.clinical_study_system.model.digital_signature.DigitalSignatureResponseDTO;
import com.inovamed.clinical_study_system.service.digital_signature.CreateDigitalSignatureService;
import com.inovamed.clinical_study_system.service.digital_signature.DeactivateDigitalSignatureService;
import com.inovamed.clinical_study_system.service.digital_signature.VerifyDigitalSignatureService;
import com.inovamed.clinical_study_system.service.token.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/digital-signature")
public class DigitalSignatureController {
    @Autowired
    private CreateDigitalSignatureService createDigitalSignatureService;
    @Autowired
    private VerifyDigitalSignatureService verifyDigitalSignatureService;
    @Autowired
    private DeactivateDigitalSignatureService deactivateDigitalSignatureService;
    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<DigitalSignatureResponseDTO> create(
            HttpServletRequest request,
            @ModelAttribute DigitalSignatureRequestDTO digitalSignatureRequestDTO,
            @RequestParam("file") MultipartFile file) throws IOException, NoSuchAlgorithmException {

        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        Long userId = tokenService.getUserIdFromToken(token);

        AttachmentRequestDTO attachmentRequestDTO = new AttachmentRequestDTO(file.getOriginalFilename(), file.getBytes(), userId);

        DigitalSignatureResponseDTO response = createDigitalSignatureService.execute(
                digitalSignatureRequestDTO,
                attachmentRequestDTO,
                userId
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/verify/{id}")
    public ResponseEntity<Boolean> verify(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(verifyDigitalSignatureService.execute(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivate(@PathVariable("id") Long id){
        this.deactivateDigitalSignatureService.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
