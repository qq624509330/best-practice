package io.spldeolin.bestpractice.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import lombok.extern.log4j.Log4j2;

@Aspect
@Log4j2
public class ControllerAspect {

    @Pointcut("execution(* io.spldeolin.bestpractice.controller.*.*(..))")
    public void performance() {}

    @Before("performance()")
    public void before() {
        log.info("请求开始");
    }

    @AfterReturning("performance()")
    public void afterReturning() {
        log.info("请求结束");
    }

}