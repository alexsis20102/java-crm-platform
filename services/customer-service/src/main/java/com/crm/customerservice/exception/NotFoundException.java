package com.crm.customerservice.exception;

import com.crm.common.exception.BusinessException;

import com.crm.common.enums.ErrorCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(String message) {
        super(
                ErrorCode.CUSTOMER_NOT_FOUND,
                message,
                404
        );
    }

}
