package io.spldeolin.bestpractice.util;

import lombok.extern.log4j.Log4j2;

/**
 * 任务工具类：用于演示spring-quartz.xml里第二种触发器的功能
 *
 * @author Deolin
 */
@Log4j2
public class BbbbJob {

    public void doJob() {
        log.info("干活bbb");
    }

}
