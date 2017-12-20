package io.spldeolin.bestpractice.util;

import lombok.extern.log4j.Log4j2;

/**
 * 初始化工具类：用于演示spring-custom-bean.xml中的init-method属性
 *
 * @author Deolin
 */
@Log4j2
public class Initializer {

    public void init() {
        log.info("调用方法[io.spldeolin.bestpractice.util.Initializer.init()]");
    }

}
