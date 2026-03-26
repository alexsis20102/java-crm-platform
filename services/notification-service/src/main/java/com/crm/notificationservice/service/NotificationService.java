package com.crm.notificationservice.service;
import com.crm.notificationservice.entity.Notification;
import com.crm.common.enums.NotificationStatus;
import com.crm.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository repository;
    private final EmailService emailService;

    public NotificationService(NotificationRepository repository,
                               EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public Notification createNotification(String eventType,
                                           String recipient,
                                           String message) {

        Notification notification = new Notification(
                eventType,
                recipient,
                message,
                NotificationStatus.PENDING
        );

        return repository.save(notification);
    }

    public void processNotification(Notification notification) {

        try {
            emailService.sendEmail(
                    notification.getRecipient(),
                    notification.getEventType(),
                    notification.getMessage()
            );

            notification.setStatus(NotificationStatus.SENT);
            notification.setSentAt(LocalDateTime.now());

        } catch (Exception e) {
            notification.setStatus(NotificationStatus.FAILED);
        }

        repository.save(notification);
    }

    public List<Notification> getAll() {
        return repository.findAll();
    }

    public List<Notification> getFailed() {
        return repository.findByStatus(NotificationStatus.FAILED);
    }
}
