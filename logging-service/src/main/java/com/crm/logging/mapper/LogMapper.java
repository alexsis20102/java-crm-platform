package com.crm.logging.mapper;

import com.crm.logging.dto.LogEvent;
import com.crm.logging.entity.LogEntry;

import java.time.LocalDateTime;

public class LogMapper {

    public static LogEntry toEntity(LogEvent event) {
        LogEntry log = new LogEntry();
        log.setServiceName(event.getServiceName());
        log.setLevel(event.getLevel());
        log.setMessage(event.getMessage());
        log.setTraceId(event.getTraceId());
        log.setTimestamp(LocalDateTime.now());
        return log;
    }

}
