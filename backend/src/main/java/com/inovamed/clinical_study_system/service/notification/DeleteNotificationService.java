package com.inovamed.clinical_study_system.service.notification;

import com.inovamed.clinical_study_system.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteNotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public String execute(Long id){
        notificationRepository.deleteById(id);
        if(notificationRepository.existsById(id)){
            return "Notification Not Deleted";
        }
        return "Notification " + id + "Deleted";
    }

}
