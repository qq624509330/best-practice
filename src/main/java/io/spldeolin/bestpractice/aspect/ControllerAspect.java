package io.spldeolin.bestpractice.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ControllerAspect {

    Logger LOG = LogManager.getLogger(ControllerAspect.class);

    @Pointcut("execution(* io.spldeolin.bestpractice.controller.*.*(..))")
    public void performance() {}

    @Before("performance()")
    public void before() {
        LOG.info("请求开始");
    }

    @AfterReturning("performance()")
    public void afterReturning() {
        LOG.info("请求结束");
    }

}