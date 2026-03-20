package com.crm.logging.controller;

import com.crm.logging.entity.LogEntry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.logging.repository.LogRepository;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    private final LogRepository repository;

    public LogController(LogRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/get-all-logs")
    public List<LogEntry> getAllLogs() {
        return repository.findAll();
    }

    @GetMapping("/trace/{traceId}")
    public List<LogEntry> getByTraceId(@PathVariable("traceId") String traceId) {
        return repository.findByTraceId(traceId);
    }

    @GetMapping("/service/{serviceName}")
    public List<LogEntry> getByService(@PathVariable("serviceName") String serviceName) {
        return repository.findByServiceName(serviceName);
    }

}
