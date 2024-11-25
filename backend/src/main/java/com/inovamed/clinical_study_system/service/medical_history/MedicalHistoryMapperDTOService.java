package com.inovamed.clinical_study_system.service.medical_history;


import com.inovamed.clinical_study_system.model.medical_history.MedicalHistory;
import com.inovamed.clinical_study_system.model.medical_history.MedicalHistoryResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryMapperDTOService {


    public MedicalHistoryResponseDTO toDTO(MedicalHistory medicalHistory){
       return new MedicalHistoryResponseDTO(medicalHistory.getText());

    }
}
