package com.cikp.mall.exception;

import com.cikp.mall.common.api.IErrorCode;

/**
 * @ClassName Asserts
 * @Description //断言处理类，用于抛出各种API异常
 * @Author ccy
 * @Date 2020/12/23 11:42
 * @Version 1.0
 **/
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}