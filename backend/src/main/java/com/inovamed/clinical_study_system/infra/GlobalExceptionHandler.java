package com.inovamed.clinical_study_system.infra;

import com.inovamed.clinical_study_system.exception.DoctorNotFoundException;
import com.inovamed.clinical_study_system.exception.PatientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}

