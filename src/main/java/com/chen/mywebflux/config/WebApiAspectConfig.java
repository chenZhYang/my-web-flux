package com.chen.mywebflux.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Aaron chen
 * @Date: 2020/5/14 1:50
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class WebApiAspectConfig {
    @Pointcut("execution(public * com.chen.mywebflux.MyWebFluxApplication.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("request data = {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("response data = {}", ret);
    }

}
