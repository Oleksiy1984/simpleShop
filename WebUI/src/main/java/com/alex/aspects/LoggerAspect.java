package com.alex.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    private Logger log = Logger.getLogger(getClass().getName());

    @Before("execution(* com.alex..*.*(..))")
    public void log(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        log.info("Called " + method + " on " + joinPoint.getTarget());
        Object[] signatureArgs = joinPoint.getArgs();
        for (Object signatureArg: signatureArgs) {
            log.info("with arg " + signatureArg);
        }

    }
}
