package com.inovamed.clinical_study_system.service.notification;

import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.notification.NotificationResponseDTO;
import com.inovamed.clinical_study_system.model.notification.NotificationResquestDTO;
import com.inovamed.clinical_study_system.model.patient.Patient;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationDTOMapperService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public NotificationResponseDTO toDTO(Notification notification) {
        return new NotificationResponseDTO(notification.getId()
                , notification.getTitle()
                , notification.getMessage()
                , notification.getAttachment()
                , notification.getRecipientsDoctors()
                , notification.getRecipientsPatients());
    }

    public Notification toEntity(NotificationResquestDTO notificationResquestDTO) {
        List<Doctor> doctors = this.doctorRepository.findAllById(notificationResquestDTO.doctorsId());
        List<Patient> patients = this.patientRepository.findAllById(notificationResquestDTO.patientsId());


        Notification notification = new Notification();
        notification.setSender(notificationResquestDTO.sender());
        notification.setSenderCode(notificationResquestDTO.id());
        notification.setTitle(notificationResquestDTO.title());
        notification.setMessage(notificationResquestDTO.message());
        notification.setAttachment(notificationResquestDTO.attachment());
        notification.setRecipientsDoctors(doctors);
        notification.setRecipientsPatients(patients);

        return notification;
    }

}
