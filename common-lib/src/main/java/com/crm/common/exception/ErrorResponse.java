package com.crm.common.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {
    private String message;
    private int status;
    private String service;
    private String errorCode;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public ErrorResponse(String message, int status, String service, String errorCode) {
        this.message = message;
        this.status = status;
        this.service = service;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String message, int status, String service, String errorCode, Map<String, String> errors) {
        this(message, status, service, errorCode);
        this.errors = errors;
    }

    public String getMessage() { return message; }
    public int getStatus() { return status; }
    public String getService() { return service; }
    public String getErrorCode() { return errorCode; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public Map<String, String> getErrors() { return errors; }
}
