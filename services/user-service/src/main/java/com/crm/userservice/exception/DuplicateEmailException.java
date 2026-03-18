package com.crm.userservice.exception;

import com.crm.common.exception.BusinessException;

import com.crm.common.enums.ErrorCode;

public class DuplicateEmailException extends BusinessException {

    public DuplicateEmailException() {
        super(
                ErrorCode.EMAIL_ALREADY_EXISTS,
                "A user with this email already exists.",
                400
        );
    }

}
