package com.inovamed.clinical_study_system.service.notification;

import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.notification.NotificationResponseDTO;
import com.inovamed.clinical_study_system.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindByIdNotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationDTOMapperService notificationDTOMapperService;

    public NotificationResponseDTO execute(Long id){
        Notification notification = notificationRepository.findById(id).orElseThrow(()->{
            return new RuntimeException("Notification Not Found");
        });

        return notificationDTOMapperService.toDTO(notification);
    }
}
