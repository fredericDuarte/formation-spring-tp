package com.training.spring.bigcorp.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MonitoredAspect {

    @Before("@annotation(com.training.springcore.aspect.Monitored)")
    public void logServiceBeforeCall(JoinPoint jp) {
        System.out.println("Appel finder " + jp.getSignature());
    }

    @AfterReturning(pointcut = "@annotation(com.training.springcore.aspect.Monitored)", returning = "element")
            public void logServiceAfterCall(JoinPoint jp, Object element) {
        if (element == null) {
            System.out.println("Finder " + jp.getTarget() + "returns null");
        } else {
            System.out.println("Finder " + jp.getTarget() + "returns "
                    + element.toString());
        }
    }

    @AfterThrowing(pointcut = "@annotation(com.training.springcore.aspect.Monitored)", throwing = "ex")
    public void logServiceAfterException(RuntimeException ex) {
        System.out.println("Error " + ex.getMessage());
    }

    @After("@annotation(com.training.springcore.aspect.Monitored)")
    public void logServiceAfter(JoinPoint jp) {
        System.out.println("Finder " + jp.getSignature() + " was called");
    }
}
