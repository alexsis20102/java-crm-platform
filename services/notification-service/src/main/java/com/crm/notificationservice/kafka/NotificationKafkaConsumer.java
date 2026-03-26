package com.crm.notificationservice.kafka;

import com.crm.common.dto.InvoiceCreatedEvent;
import com.crm.notificationservice.entity.Notification;
import com.crm.notificationservice.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationKafkaConsumer {
    private final NotificationService notificationService;

    public NotificationKafkaConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "invoice-created", groupId = "notification-group")
    public void handleInvoiceCreated(InvoiceCreatedEvent event) {

        System.out.println("InvoiceCreatedEvent received");

        Notification notification = notificationService.createNotification(
                "INVOICE_CREATED",
                event.getEmail(),
                "Invoice #" + event.getInvoiceId() + " created. Amount: " + event.getAmount()
        );

        notificationService.processNotification(notification);
    }
}
