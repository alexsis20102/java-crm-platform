package com.crm.logging.repository;

import com.crm.logging.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<LogEntry, Long> {
    List<LogEntry> findByTraceId(String traceId);

    List<LogEntry> findByServiceName(String serviceName);
}
