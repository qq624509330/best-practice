package io.spldeolin.bestpractice.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 任务工具类：用于演示spring-quartz.xml里第一种触发器的功能
 *
 * @author Deolin
 */
public class AaaaJob {

    private static final Logger LOG = LogManager.getLogger(AaaaJob.class);

    public void doJob() {
        LOG.info("干活aaa");
    }

}
