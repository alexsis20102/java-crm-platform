package com.crm.notificationservice.controller;

import com.crm.notificationservice.dto.NotificationResponse;
import com.crm.notificationservice.entity.Notification;
import com.crm.notificationservice.mapper.NotificationMapper;
import com.crm.notificationservice.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/get-all-notifications")
    public List<NotificationResponse> getAll() {
        return service.getAll()
                .stream()
                .map(NotificationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/failed")
    public List<NotificationResponse> getFailed() {
        return service.getFailed()
                .stream()
                .map(NotificationMapper::toResponse)
                .collect(Collectors.toList());
    }
}
