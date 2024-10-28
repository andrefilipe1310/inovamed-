package com.inovamed.clinical_study_system.model.application;

public enum StatusApplication {
    APPROVAL("approval"),
    REJECTION("rejection"),
    EXPULSION("expulsion");

    private String status;

    StatusApplication(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
