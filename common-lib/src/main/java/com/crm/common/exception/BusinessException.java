package com.crm.common.exception;

import com.crm.common.enums.ErrorCode;

public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    private final int status;

    public BusinessException(ErrorCode errorCode, String message, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public int getStatus() {
        return status;
    }


}