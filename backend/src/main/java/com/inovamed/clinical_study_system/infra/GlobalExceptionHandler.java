package com.inovamed.clinical_study_system.infra;

import com.inovamed.clinical_study_system.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<RestExceptionError> handlerPatientNotFoundException(PatientNotFoundException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<RestExceptionError> handleDoctorNotFoundException(DoctorNotFoundException exception) {
        RestExceptionError threatError = new RestExceptionError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }

    @ExceptionHandler(ClinicalRepresentativeNotFoundException.class)
    public ResponseEntity<RestExceptionError> handleClinicalRepresentativeNotFoundException(ClinicalRepresentativeNotFoundException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<RestExceptionError> handleUserAlreadyExistsException(UserAlreadyExistsException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.CONFLICT,"User already exists");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatError);
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public  ResponseEntity<RestExceptionError> hadleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException exception){
        RestExceptionError threatError = new RestExceptionError(HttpStatus.CONFLICT,"Email Already Registered Exception");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestExceptionError> handleAuthenticationException(AuthenticationException exception) {
        RestExceptionError threatError = new RestExceptionError(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatError);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RestExceptionError> handleBadCredentialsException(BadCredentialsException exception) {
        RestExceptionError threatError = new RestExceptionError(HttpStatus.UNAUTHORIZED, "Credenciais inválidas. Verifique seu email e senha.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatError);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<RestExceptionError> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        RestExceptionError threatError = new RestExceptionError(HttpStatus.UNAUTHORIZED, "Usuário não encontrado com o email fornecido.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatError);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<RestExceptionError> handleUserNotFoundException(UserNotFoundException exception) {
        RestExceptionError threatError = new RestExceptionError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }


}

