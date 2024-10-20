package com.snut.material.common;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/*
    aop日志统一打印的实现类
*/
@Component
@Aspect
public class LogAspect {

    //获取日志对象
    private Logger logger = LoggerFactory.getLogger(getClass());

    //通配符，定义规则，哪些方法可以被我的切面切到，这里的意思是不限返回值和方法和参数
    //定义切入点
    @Pointcut("execution(public * com.snut.material.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取所有请求头数据
        logger.info("URL : {}", request.getRequestURL().toString());
        logger.info("HTTP_METHOD :{} ", request.getMethod());
        logger.info("IP : {}", request.getRemoteAddr());
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String hearName = (String) headers.nextElement();
            logger.info("name:{},value:{}", hearName, request.getHeader(hearName));
        }
        //获取所有请求参数
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            logger.info("name:{},value:{}", name, request.getParameter(name));
        }



    }
}
