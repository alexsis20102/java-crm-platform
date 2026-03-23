package com.crm.orderservice.exception;

import com.crm.common.enums.ErrorCode;
import com.crm.common.exception.BusinessException;

public class NotFoundException extends BusinessException {
    public NotFoundException(String message) {
        super(
                ErrorCode.ORDER_NOT_FOUND,
                message,
                404
        );
    }
}
