package com.snut.material.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

//统一的异常处理实现类
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 针对请求 404 处理
     * 访问spring后,解析控制器地址找不到
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonResult noHandleFoundException(NoHandlerFoundException e) {
        CommonResult commonResult = new CommonResult(404,e.getMessage());
        return commonResult;
    }


    /**
     * 针对其他异常处理
     */
    @ExceptionHandler(Exception.class)
    public CommonResult globalException(Exception e) {
        CommonResult commonResult = new CommonResult(500,e.getMessage());
        //出现异常了,进行异常日志信息打印
        return commonResult;
    }
}
