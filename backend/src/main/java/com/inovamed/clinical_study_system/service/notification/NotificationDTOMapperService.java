package com.inovamed.clinical_study_system.service.notification;

import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.model.attachment.Attachment;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.notification.NotificationResponseDTO;
import com.inovamed.clinical_study_system.model.notification.NotificationResquestDTO;
import com.inovamed.clinical_study_system.model.patient.Patient;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationDTOMapperService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ClinicalStudyRepresentiveRepository clinicalRepository;

    public NotificationResponseDTO toDTO(Notification notification) {
        return new NotificationResponseDTO(
                notification.getId(),
                notification.getTitle(),
                notification.getMessage(),
                notification.getAttachment(),
                notification.getStudyRepresentative().getName(),
                notification.getRecipientsDoctors().stream()
                        .map(doctor -> "successfully confirmed receipt of " + doctor.getName() + " " + doctor.getPublicKey())
                        .collect(Collectors.toList()),
                notification.getRecipientsPatients().stream()
                        .map(patient -> "successfully confirmed receipt of " + patient.getName() + " " + patient.getCode())
                        .collect(Collectors.toList())
        );
    }

    public Notification toEntity(NotificationResquestDTO notificationResquestDTO, List<MultipartFile> files) throws IOException {
        ClinicalStudyRepresentative clinicalRepresentative = clinicalRepository.findById(notificationResquestDTO.sender()).orElseThrow(
                ()->{
                    return new ClinicalRepresentativeNotFoundException();
                }
        );
        List<Doctor> doctors = this.doctorRepository.findAllById(notificationResquestDTO.doctorsId());
        List<Patient> patients = this.patientRepository.findAllById(notificationResquestDTO.patientsId());



        Notification notification = new Notification();
        notification.setSender(notificationResquestDTO.sender());
        notification.setSenderCode(notification.getId());
        notification.setTitle(notificationResquestDTO.title());
        notification.setMessage(notificationResquestDTO.message());
        notification.setAttachment(files.stream().map((file)->{
            Attachment attachment = new Attachment();
            attachment.setUser(clinicalRepresentative);
            try {
                attachment.setArchive(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return attachment;
        }).collect(Collectors.toList()));
        notification.setRecipientsDoctors(doctors);
        notification.setRecipientsPatients(patients);
        notification.setStudyRepresentative(clinicalRepresentative);
        System.out.println("aaaaaaaaaaa");
        System.out.println(Arrays.toString(notification.getAttachment().get(0).getArchive()));
        return notification;
    }

}
