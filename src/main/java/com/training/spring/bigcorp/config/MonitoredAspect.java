package com.training.spring.bigcorp.config;

import com.training.spring.bigcorp.service.SiteService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MonitoredAspect { 
    
    private final static Logger logger = LoggerFactory.getLogger(SiteService.class);
    
    

    @Before("@annotation(com.training.spring.bigcorp.aspect.Monitored)")
    public void logServiceBeforeCall(JoinPoint jp) {
        logger.debug("Appel @Before :: " + jp.getSignature());
    }

    @AfterReturning(pointcut = "@annotation(com.training.spring.bigcorp.aspect.Monitored)", returning = "element")
            public void logServiceAfterCall(JoinPoint jp, Object element) {
        if (element == null) {
            logger.debug("Appel @AfterReturning :: " + jp.getTarget() + "returns null");
        } else {
            logger.debug("Appel @AfterReturning :: " + jp.getTarget() + "returns "
                    + element.toString());
        }
    }

    @AfterThrowing(pointcut = "@annotation(com.training.spring.bigcorp.aspect.Monitored)", throwing = "ex")
    public void logServiceAfterException(RuntimeException ex) {
        logger.error("Error  @AfterThrowing :: ",ex.getMessage());
    }

    @After("@annotation(com.training.spring.bigcorp.aspect.Monitored)")
    public void logServiceAfter(JoinPoint jp) {
        logger.debug("Appel @After :: " + jp.getSignature() + " was called");
    }
}
