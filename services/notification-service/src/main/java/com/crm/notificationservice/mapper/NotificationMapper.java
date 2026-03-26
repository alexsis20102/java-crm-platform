package com.crm.notificationservice.mapper;

import com.crm.notificationservice.dto.NotificationResponse;
import com.crm.notificationservice.entity.Notification;

public class NotificationMapper {

    public static NotificationResponse toResponse(Notification notification) {
        NotificationResponse response = new NotificationResponse();

        response.setId(notification.getId());
        response.setEventType(notification.getEventType());
        response.setRecipient(notification.getRecipient());
        response.setMessage(notification.getMessage());
        response.setStatus(notification.getStatus().name());
        response.setCreatedAt(notification.getCreatedAt());
        response.setSentAt(notification.getSentAt());

        return response;
    }

}
