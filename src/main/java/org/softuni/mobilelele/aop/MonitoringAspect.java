package org.softuni.mobilelele.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softuni.mobilelele.service.MonitoringService;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Aspect
@Component
public class MonitoringAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringAspect.class);
    private final MonitoringService monitoringService;

    public MonitoringAspect(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Before("Pointcuts.trackOfferSearch()")
    public void logOfferSearch() {
        this.monitoringService.logOfferSearch();
    }

    @Around("Pointcuts.warnIfTimeExceeds()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        WarnIfExecutionExceeds annotation = getAnnotation(joinPoint);

        long time = annotation.timeInMillis();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object proceed = joinPoint.proceed();

        stopWatch.stop();

        if (stopWatch.getLastTaskTimeMillis() > time) {
            LOGGER.warn("The method {} ran for {} millis which is more than expected {} millis.",
                    joinPoint.getSignature(), stopWatch.getLastTaskTimeMillis(), time);
        }
        return proceed;
    }

    private static WarnIfExecutionExceeds getAnnotation(ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            return joinPoint
                    .getTarget()
                    .getClass()
                    .getMethod(method.getName(), method.getParameterTypes())
                    .getAnnotation(WarnIfExecutionExceeds.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
