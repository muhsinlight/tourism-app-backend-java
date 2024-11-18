package com.teknofest.turizm.logging;

import com.teknofest.turizm.service.LoggingService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DatabaseLoggingAspect {

    private final LoggingService loggingService;

    public DatabaseLoggingAspect(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Pointcut("execution(* com.teknofest.turizm.repository.*.*(..))")
    public void repositoryMethods() {}

    @Before("repositoryMethods()")
    public void logBeforeDatabaseCall(ProceedingJoinPoint joinPoint) {
        loggingService.saveLog("Veritabanı çağrısı başlatıldı", joinPoint.getSignature().getName());
    }

    @After("repositoryMethods()")
    public void logAfterDatabaseCall(ProceedingJoinPoint joinPoint) {
        loggingService.saveLog("Veritabanı çağrısı tamamlandı", joinPoint.getSignature().getName());
    }
}