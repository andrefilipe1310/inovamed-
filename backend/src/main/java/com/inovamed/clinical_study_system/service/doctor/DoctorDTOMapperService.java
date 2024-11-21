package com.inovamed.clinical_study_system.service.doctor;

import com.inovamed.clinical_study_system.exception.UserAlreadyExistsException;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.doctor.DoctorRequestDTO;
import com.inovamed.clinical_study_system.model.doctor.DoctorResponseDTO;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import com.inovamed.clinical_study_system.service.application.ApplicationMapperDTOService;
import com.inovamed.clinical_study_system.service.notification.NotificationDTOMapperService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class DoctorDTOMapperService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ApplicationMapperDTOService applicationMapperDTOService;
    @Autowired
    private NotificationDTOMapperService notificationDTOMapperService;
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
                doctor.getApplicationsSubmitted() == null ? List.of():doctor.getApplicationsSubmitted().stream().map( application -> {
                    return applicationMapperDTOService.toDTO(application);
                }).collect(Collectors.toList()),
                doctor.getNotifications() == null ? List.of() : doctor.getNotifications().stream().map(notification -> {
                    return notificationDTOMapperService.toDTO(notification);
                }).collect(Collectors.toList()),
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
