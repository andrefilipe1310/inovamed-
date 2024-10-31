package com.inovamed.clinical_study_system.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class RestExceptionError {

    private HttpStatus statusCode;
    private String message;

}
