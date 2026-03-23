package com.crm.orderservice.exception;

import com.crm.common.enums.ErrorCode;
import com.crm.common.exception.BusinessException;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(String message) {
        super(
                ErrorCode.PRODUCT_NOT_FOUND,
                message,
                404
        );
    }
}
