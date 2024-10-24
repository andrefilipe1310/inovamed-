package com.inovamed.clinical_study_system.model.consent;

public enum ConsentType {
    DATA_PROCESSING("Processamento de Dados"),
    DATA_SHARING("Compartilhamento de Dados"),
    MARKETING("Marketing"),
    RESEARCH("Pesquisa"),
    COMMUNICATION("Comunicação");
    private final String description;

    ConsentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
