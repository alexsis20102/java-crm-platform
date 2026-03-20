package com.crm.logging.service;

import com.crm.common.dto.LogEvent;
import com.crm.logging.entity.LogEntry;
import com.crm.logging.mapper.LogMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.crm.logging.repository.LogRepository;


@Service
public class LogConsumer {

    private final LogRepository repository;

    public LogConsumer(LogRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "logs", groupId = "logging-group")
    public void consume(LogEvent event) {
        try {

            LogEntry log = LogMapper.toEntity(event);
            repository.save(log);

        } catch (Exception e) {
            System.err.println("Error processing log event: " + e.getMessage());
        }
    }
}
