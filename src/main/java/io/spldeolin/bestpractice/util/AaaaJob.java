package io.spldeolin.bestpractice.util;

import lombok.extern.log4j.Log4j2;

/**
 * 任务工具类：用于演示spring-quartz.xml里第一种触发器的功能
 *
 * @author Deolin
 */
@Log4j2

public class AaaaJob {

    public void doJob() {
        log.info("干活aaa");
    }

}
