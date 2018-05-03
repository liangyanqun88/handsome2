package com.jrl.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {
    private  final  static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.jrl.controller.*.*(..))")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
//        logger.info("url={}", request.getRequestURL());

//        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
//        logger.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void  doAfter(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        logger.info("after url = {}",request.getRequestURL());


    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
//        if (object != null) {
//            logger.info("response = {}", object.toString());
//        }
    }
}
