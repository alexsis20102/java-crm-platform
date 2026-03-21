package com.crm.customerservice.service;

import com.crm.common.dto.LogEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;



import java.time.Instant;
import java.util.UUID;

@Component
public class LogProducer {

    private final KafkaTemplate<String, LogEvent> kafkaTemplate;

    @Value("${spring.application.name}")
    private String serviceName;

    public LogProducer(KafkaTemplate<String, LogEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(String level, String message) {
        sendLog(level, message, null);
    }

    public void sendLog(String level, String message, String traceId) {

        if (traceId == null) traceId = UUID.randomUUID().toString();
        String timestamp = Instant.now().toString();

        LogEvent log = new LogEvent(level, message, timestamp, serviceName, traceId);
        kafkaTemplate.send("logs", log);
    }

}
