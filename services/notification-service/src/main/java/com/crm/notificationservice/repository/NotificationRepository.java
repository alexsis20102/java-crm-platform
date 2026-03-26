package com.crm.notificationservice.repository;

import com.crm.notificationservice.entity.Notification;
import com.crm.common.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

    List<Notification> findByStatus(NotificationStatus status);

}
