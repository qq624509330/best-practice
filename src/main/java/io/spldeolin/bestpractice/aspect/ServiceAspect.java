package io.spldeolin.bestpractice.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ServiceAspect {

    Logger LOG = LogManager.getLogger(ServiceAspect.class);

    @Pointcut("execution(* io.spldeolin.bestpractice.service.*.*(..))")
    public void performance() {}

    @Before("performance()")
    public void before() {
        LOG.info("业务开始");
    }

    @AfterReturning("performance()")
    public void afterReturning() {
        LOG.info("业务结束");
    }

    @AfterThrowing(value = "performance()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        LOG.error("业务异常： " + "异常类型[" + e.getClass().getSimpleName() + "]，发生地点[" + className + "." + methodName + "]");
    }

}
