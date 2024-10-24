package com.inovamed.clinical_study_system.service.notification;

import com.inovamed.clinical_study_system.model.notification.NotificationResponseDTO;
import com.inovamed.clinical_study_system.model.notification.NotificationResquestDTO;
import com.inovamed.clinical_study_system.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationDTOMapperService notificationDTOMapperService;

    public NotificationResponseDTO execute(NotificationResquestDTO notificationResquestDTO){
        return notificationDTOMapperService.toDTO(notificationRepository.save(notificationDTOMapperService.toEntity(notificationResquestDTO)));
    }
}
