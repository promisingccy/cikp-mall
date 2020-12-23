package com.cikp.mall.exception;

import com.cikp.mall.common.api.IErrorCode;

/**
 * @ClassName ApiException
 * @Description //自定义API异常
 * @Author ccy
 * @Date 2020/12/23 11:42
 * @Version 1.0
 **/
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}