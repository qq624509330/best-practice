package io.spldeolin.bestpractice.util;

import org.springframework.context.ApplicationContextAware;

/**
 * 代理工具类：持有并代理application-context.xml里的内容
 *
 * @author Deolin
 */
public class ApplicationContext implements ApplicationContextAware {

    private static org.springframework.context.ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) {
        ApplicationContext.applicationContext = applicationContext;
    }

    public static org.springframework.context.ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

}