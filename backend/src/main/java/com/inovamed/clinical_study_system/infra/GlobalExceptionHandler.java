package com.inovamed.clinical_study_system.infra;

import com.inovamed.clinical_study_system.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.NoSuchAlgorithmException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // DOCTOR EXCEPTIONS

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<RestExceptionError> handleDoctorNotFoundException(DoctorNotFoundException exception) {
        RestExceptionError threatError = new RestExceptionError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }

    @ExceptionHandler(DoctorDeletionFailedException.class)
    public ResponseEntity<RestExceptionError> handlerDoctorDeletionFailedException(DoctorDeletionFailedException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatError);
    }

    // PATIENT EXCEPTIONS

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<RestExceptionError> handlerPatientNotFoundException(PatientNotFoundException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }

    @ExceptionHandler(PatientDeletionFailedException.class)
    public ResponseEntity<RestExceptionError> handlerPatientDeletionFailedException(PatientDeletionFailedException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatError);
    }

    // CLINICAL REPRESENTATIVE EXCEPTIONS

    @ExceptionHandler(ClinicalRepresentativeNotFoundException.class)
    public ResponseEntity<RestExceptionError> handleClinicalRepresentativeNotFoundException(ClinicalRepresentativeNotFoundException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }

    @ExceptionHandler(ClinicalRepresentativeDeletionFailedException.class)
    public ResponseEntity<RestExceptionError> handlerClinicalRepresentativeDeletionFailedException(ClinicalRepresentativeDeletionFailedException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatError);
    }

    // USER EXCEPTIONS

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<RestExceptionError> handleUserNotFoundException(UserNotFoundException exception) {
        RestExceptionError threatError = new RestExceptionError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<RestExceptionError> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        RestExceptionError threatError = new RestExceptionError(HttpStatus.UNAUTHORIZED, "Usuário não encontrado com o email fornecido.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatError);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<RestExceptionError> handleUserAlreadyExistsException(UserAlreadyExistsException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.CONFLICT,"User already exists");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatError);
    }

    // ATTACHMENT EXCEPTIONS

    @ExceptionHandler(AttachmentNotFoundException.class)
    public ResponseEntity<RestExceptionError> handlerAttachmentNotFoundException(AttachmentNotFoundException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }

    @ExceptionHandler(AttachmentNotDeletedException.class)
    public ResponseEntity<RestExceptionError> handlerAttachmentNotDeletedException(AttachmentNotDeletedException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatError);
    }

    // SIGNATURE EXCEPTIONS

    @ExceptionHandler(SignatureNotFoundException.class)
    public ResponseEntity<RestExceptionError> handlerSignatureNotFoundException(SignatureNotFoundException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }

    @ExceptionHandler(SignatureIsInactiveException.class)
    public ResponseEntity<RestExceptionError> handlerSignatureIsInactiveException(SignatureIsInactiveException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatError);
    }

    @ExceptionHandler(SignatureErrorVerifyException.class)
    public ResponseEntity<RestExceptionError> handlerSignatureErrorVerifyException(SignatureErrorVerifyException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.INTERNAL_SERVER_ERROR , exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatError);
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<RestExceptionError> handleFailedToGenerateKeyPairException(FailedToGenerateKeyPairException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatError);
    }

    @ExceptionHandler(FailedToSignDocumentException.class)
    public ResponseEntity<RestExceptionError> handleFailedToSignDocumentException(FailedToSignDocumentException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatError);
    }

    @ExceptionHandler(InvalidSignatureValidityException.class)
    public ResponseEntity<RestExceptionError> handlerInvalidSignatureValidityException(InvalidSignatureValidityException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatError);
    }

    // NOTIFICATION EXCEPTIONS

    @ExceptionHandler(NotificationDeletionFailedException.class)
    public ResponseEntity<RestExceptionError> handlerNotificationDeletionFailedException(NotificationDeletionFailedException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatError);
    }

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<RestExceptionError> handlerNotificationNotFoundException(NotificationNotFoundException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }



    // RESEARCH EXCEPTION

    @ExceptionHandler(ResearchDeletionFailedException.class)
    public ResponseEntity<RestExceptionError> handlerResearchDeletionFailedException(ResearchDeletionFailedException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatError);
    }

    // EMAIL EXCEPTIONS

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public  ResponseEntity<RestExceptionError> hadleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.CONFLICT,"Email Already Registered Exception");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatError);
    }

    //AUTHENTICATION EXCEPTIONS

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestExceptionError> handleAuthenticationException(AuthenticationException exception) {
        RestExceptionError threatError = new RestExceptionError(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatError);
    }

    // CREDENTIALS EXCEPTIONS

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RestExceptionError> handleBadCredentialsException(BadCredentialsException exception) {
        RestExceptionError threatError = new RestExceptionError(HttpStatus.UNAUTHORIZED, "Credenciais inválidas. Verifique seu email e senha.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatError);
    }

    // TOKEN EXCEPTIONS

    @ExceptionHandler(TokenGenerationException.class)
    public ResponseEntity<RestExceptionError> handlerTokenGenerationException(TokenGenerationException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatError);
    }

    @ExceptionHandler(MissingSecretKeyException.class)
    public ResponseEntity<RestExceptionError> handleMissingSecretKeyException(MissingSecretKeyException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatError);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<RestExceptionError> handleTokenNotFound(TokenNotFoundException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }



}

