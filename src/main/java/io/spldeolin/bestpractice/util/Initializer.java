package io.spldeolin.bestpractice.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 初始化工具类：用于演示spring-custom-bean.xml中的init-method属性
 *
 * @author Deolin
 */
public class Initializer {

    private static final Logger LOG = LogManager.getLogger(Initializer.class);

    public void init() {
        LOG.info("调用方法[io.spldeolin.bestpractice.util.Initializer.init()]");
    }

}
