package io.spldeolin.bestpractice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import lombok.extern.log4j.Log4j2;

@Aspect
@Log4j2
public class ServiceAspect {

    @Pointcut("execution(* io.spldeolin.bestpractice.service..*.*(..))")
    public void performance() {}

    @Before("performance()")
    public void before() {
        log.info("业务开始");
    }

    @AfterReturning("performance()")
    public void afterReturning() {
        log.info("业务结束");
    }

    @AfterThrowing(value = "performance()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.error("业务异常： " + "异常类型[" + e.getClass().getSimpleName() + "]，发生地点[" + className + "." + methodName + "]");
    }

}
