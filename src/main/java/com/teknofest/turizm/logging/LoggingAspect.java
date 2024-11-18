package com.teknofest.turizm.logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.teknofest.turizm.*.*(..))")
    public void apiMethods() {}

    @Before("apiMethods()")
    public void logBefore() {
        logger.info("API çağrısı başlatıldı");
    }

    @AfterReturning("apiMethods()")
    public void logAfterReturning() {
        logger.info("API çağrısı başarıyla tamamlandı");
    }

    @AfterThrowing("apiMethods()")
    public void logAfterThrowing() {
        logger.error("API çağrısı sırasında hata oluştu");
    }

    @After("apiMethods()")
    public void logAfter() {
        logger.info("API çağrısı tamamlandı");
    }
}
