package com.crm.userservice.exception;

import com.crm.common.exception.BusinessException;

import com.crm.common.enums.ErrorCode;
public class NoUserException extends BusinessException {

    public NoUserException() {
        super(
                ErrorCode.USER_NOT_FOUND,
                "User not found",
                400
        );
    }

}
