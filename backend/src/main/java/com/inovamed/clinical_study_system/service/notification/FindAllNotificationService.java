package com.inovamed.clinical_study_system.service.notification;

import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.notification.NotificationResponseDTO;
import com.inovamed.clinical_study_system.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllNotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationDTOMapperService notificationDTOMapperService;

    public List<NotificationResponseDTO> execute(Long userId){
        List<Notification> notifications = notificationRepository.findAllByRecipientsPatients_Id(userId);
        System.out.println(notifications.size());
        return notifications.stream().map(notification -> {
            return  notificationDTOMapperService.toDTO(notification);
        }).collect(Collectors.toList());


    }
}
