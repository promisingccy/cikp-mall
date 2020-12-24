package com.cikp.mall.exception;

import com.cikp.mall.common.api.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName GlobalExceptionHandler
 * @Description //全局异常处理
 * @Author ccy
 * @Date 2020/12/23 10:31
 * @Version 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public CommonResult handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return CommonResult.failed("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public CommonResult notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return CommonResult.failed("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return CommonResult.failed("服务器错误，请联系管理员");
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult exceptionHandler(MethodArgumentNotValidException e) {
        return handleApiException(e.getBindingResult());
        // ArrayList<String> errorList = new ArrayList<>();
        // for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
        //     errorList.add(fieldError.getDefaultMessage());
        // }
        // String errorMessage = errorList.stream().collect(Collectors.joining(",", "[", "]"));
        // return CommonResult.failed(errorMessage);
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public CommonResult validationExceptionHandler(BindException e) {
        return handleApiException(e.getBindingResult());
        // ArrayList<String> errorList = new ArrayList<>();
        // for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
        //     errorList.add(fieldError.getDefaultMessage());
        // }
        // String errorMessage = errorList.stream().collect(Collectors.joining(",", "[", "]"));
        // return CommonResult.failed(errorMessage);
    }

    private CommonResult handleApiException(BindingResult bindingResult) {
        ArrayList<String> errorList = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorList.add(fieldError.getDefaultMessage());
        }
        String errorMessage = errorList.stream().collect(Collectors.joining(",", "[", "]"));
        return CommonResult.failed(errorMessage);
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        return CommonResult.failed(String.join(",",msgList));
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ApiException.class)
    public CommonResult businessException(ApiException e) {
        log.error(e.getMessage(), e);
        return CommonResult.failed(e.getMessage());
    }
    //
    // /**
    //  * 演示模式异常
    //  */
    // @ExceptionHandler(DemoModeException.class)
    // public AjaxResult demoModeException(DemoModeException e) {
    //     return AjaxResult.error("演示模式，不允许操作");
    // }
    //
    // /**
    //  * 权限校验失败 如果请求为ajax返回json，普通请求跳转页面
    //  */
    // @ExceptionHandler(AuthorizationException.class)
    // public Object handleAuthorizationException(HttpServletRequest request, AuthorizationException e) {
    //     //log.error(e.getMessage(), e);
    //     if (ServletUtils.isAjaxRequest(request)) {
    //         return AjaxResult.unauth(PermissionUtils.getMsg(e.getMessage()));
    //     } else {
    //         ModelAndView modelAndView = new ModelAndView();
    //         modelAndView.setViewName("error/unauth");
    //         return modelAndView;
    //     }
    // }
}
