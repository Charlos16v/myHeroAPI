package com.example.myHero.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomTimedAspect {

    @Around("@annotation(customTimed)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, CustomTimed customTimed) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        String methodName = joinPoint.getSignature().toShortString();
        String customMessage = customTimed.value();

        String logMessage = customMessage.isEmpty() ? methodName : customMessage;

        System.out.println(logMessage + " request executed in " + executionTime + " ms");

        return result;
    }
}


