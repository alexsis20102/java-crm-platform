package com.crm.notificationservice.exception;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import com.crm.common.exception.BusinessException;
import com.crm.common.exception.ErrorResponse;
import com.crm.common.enums.ErrorCode;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Value("${spring.application.name}")
    private String serviceName;

    // Business exceptions
    @ExceptionHandler(BusinessException.class)
    public ErrorResponse handleBusiness(BusinessException ex) {
        return new ErrorResponse(
                ex.getMessage(),
                ex.getStatus(),
                serviceName,
                ex.getErrorCode().name()
        );
    }

    //  1. DTO validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return new ErrorResponse(
                "Validation error",
                400,
                serviceName,
                ErrorCode.VALIDATION_ERROR.name(),
                errors
        );
    }


    // fallback DB
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneral() {
        return new ErrorResponse(
                "Internal Server Error",
                500,
                serviceName,
                ErrorCode.INTERNAL_ERROR.name()
        );
    }
}
