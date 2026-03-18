package com.crm.auth.exception;

import com.crm.common.exception.BusinessException;

import com.crm.common.enums.ErrorCode;

public class UserNotFound extends BusinessException {

    public UserNotFound() {
        super(
                ErrorCode.USER_NOT_FOUND,
                "User not found or login/password combination is incorrect",
                400
        );
    }

}
