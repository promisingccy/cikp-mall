package com.cikp.mall.common.api;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ClassName ResultCode
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/15 16:22
 * @Version 1.0
 **/
public enum ResultCode implements IErrorCode {
    SUCCESS(20000, "操作成功"),
    FAILED(40000, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
