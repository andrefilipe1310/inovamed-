package com.inovamed.clinical_study_system.service.doctor;

import com.inovamed.clinical_study_system.exception.UserAlreadyExistsException;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.doctor.DoctorRequestDTO;
import com.inovamed.clinical_study_system.model.doctor.DoctorResponseDTO;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class DoctorDTOMapperService {
    @Autowired
    private DoctorRepository doctorRepository;
    public DoctorResponseDTO toDTO(Doctor doctor){
        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getKey(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPassword(),
                doctor.getRoles(),
                doctor.getDoctorExperienceEnum(),
                doctor.getClinic(),
                doctor.getPhone(),
                doctor.getSpecialty(),
                doctor.getCrm(),
                doctor.getApplicationsSubmitted() == null ? List.of():doctor.getApplicationsSubmitted(),
                doctor.getNotifications() == null ? List.of() : doctor.getNotifications(),
                doctor.getPatients() == null ? List.of() : doctor.getPatients().stream().map(patient -> {
                    return patient.getName();
                }).collect(Collectors.toList())
        );
    }
    public Doctor toEntity(DoctorRequestDTO doctorRequestDTO){
        if(doctorRepository.findByEmail(doctorRequestDTO.email()) != null){
            throw  new UserAlreadyExistsException();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(doctorRequestDTO.password());
        Doctor doctor = new Doctor();
        doctor.setName(doctorRequestDTO.name());
        doctor.setEmail(doctorRequestDTO.email());
        doctor.setRoles(doctorRequestDTO.roles());
        doctor.setClinic(doctorRequestDTO.clinic());
        doctor.setSpecialty(doctorRequestDTO.specialty());
        doctor.setDoctorExperienceEnum(doctorRequestDTO.doctorExperienceEnum());
        doctor.setCrm(doctorRequestDTO.Crm());
        doctor.setPhone(doctorRequestDTO.phone());
        doctor.setPassword(encryptedPassword);

        return doctor;
    }
}
