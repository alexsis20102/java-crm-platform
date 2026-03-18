package com.crm.customerservice.exception;

import com.crm.common.exception.BusinessException;

import com.crm.common.enums.ErrorCode;

public class DuplicateEmailException extends BusinessException {

    public DuplicateEmailException() {
        super(
                ErrorCode.EMAIL_ALREADY_EXISTS,
                "A client with this email already exists.",
                400
        );
    }

}
