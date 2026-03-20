package com.crm.common.dto;

public class LogEvent {
    private String timestamp;
    private String serviceName;
    private String level;
    private String message;
    private String traceId;

    public LogEvent() {

    }
    public LogEvent(String level, String message, String timestamp) {
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
    }

    public LogEvent(String level, String message, String timestamp, String serviceName, String traceId) {
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
        this.serviceName = serviceName;
        this.traceId = traceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
