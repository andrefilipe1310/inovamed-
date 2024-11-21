package com.inovamed.clinical_study_system.service.notification;

import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.notification.NotificationResponseDTO;
import com.inovamed.clinical_study_system.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllNotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationDTOMapperService notificationDTOMapperService;
    @Transactional
    public List<NotificationResponseDTO> execute(Long userId,String role){
        List<Notification> notifications = new ArrayList<>();
        if (role.equals("DOCTOR")){
            notifications  = notificationRepository.findAllByRecipientsDoctors_Id(userId);
        }
        if (role.equals("PATIENT")){
            notifications  = notificationRepository.findAllByRecipientsPatients_Id(userId);
        }


        return notifications.stream().map(notification -> {
            return  notificationDTOMapperService.toDTO(notification);
        }).collect(Collectors.toList());


    }
}
