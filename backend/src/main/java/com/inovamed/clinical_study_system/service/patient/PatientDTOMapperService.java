package com.inovamed.clinical_study_system.service.patient;

import com.inovamed.clinical_study_system.exception.UserAlreadyExistsException;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.patient.Patient;
import com.inovamed.clinical_study_system.model.patient.PatientRequestDTO;
import com.inovamed.clinical_study_system.model.patient.PatientResponseDTO;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientDTOMapperService {

    @Autowired
    PatientRepository patientRepository;
    public PatientResponseDTO toDTO(Patient patient){

        String doctorName = patient.getDoctor() != null ? patient.getDoctor().getName() : "";
        String doctorCrm = patient.getDoctor() != null ? patient.getDoctor().getCrm() : "";

        return new PatientResponseDTO(
                patient.getName(),
                patient.getEmail(),
                patient.getPassword(),
                patient.getRoles(),
                patient.getCode(),
                patient.getGender(),
                patient.getBirth(),
                patient.getDigitalSignatureConsent(),
                patient.getResponsibleDoctor(),
                patient.getAuthorizations(),
                patient.getResearches(),
                patient.getNotifications(),
                patient.getSignature(),
                doctorName,
                doctorCrm
        );

    }

    public Patient toEntity(PatientRequestDTO patientRequestDTO, Doctor doctor) {
        if(patientRepository.findByEmail(patientRequestDTO.email()) != null){
            throw new UserAlreadyExistsException();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(patientRequestDTO.password());
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.name());
        patient.setEmail(patientRequestDTO.email());
        patient.setGender(patientRequestDTO.gender());
        patient.setRoles(patientRequestDTO.roles());
        patient.setBirth(patientRequestDTO.birth());
        patient.setPhone(patientRequestDTO.phone());
        patient.setPassword(encryptedPassword);
        patient.setMedicalHistory(patientRequestDTO.medicalHistory());
        patient.setSignature(patientRequestDTO.signature());
        patient.setDoctor(doctor);
        patient.setDigitalSignatureConsent(patientRequestDTO.signature() == null);
        patient.setResponsibleDoctor(true);
        patient.setAuthorizations(List.of("Permissão para usar dados","Permissão para notificar"));
        patient.setResearches(List.of());
        patient.setNotifications(List.of());

        return patient;
    }

}
