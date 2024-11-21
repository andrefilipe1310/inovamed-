package com.inovamed.clinical_study_system.repository;

import com.inovamed.clinical_study_system.model.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByRecipientsPatients_Id( Long patientId);
    List<Notification> findAllByRecipientsDoctors_Id(Long doctorId);
}
