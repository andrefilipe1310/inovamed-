package com.inovamed.clinical_study_system.exception;

public class ClinicalRepresentativeNotFoundException extends RuntimeException {
    public  ClinicalRepresentativeNotFoundException(){ super ("ClinicalRepresentative not found.");}
    public ClinicalRepresentativeNotFoundException(String message) {
        super(message);
    }
}
