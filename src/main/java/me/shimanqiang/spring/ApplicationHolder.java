package me.shimanqiang.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author shimanqiang
 * @since 2018/2/11 下午6:11
 */

@Configuration
public class ApplicationHolder implements ApplicationContextAware, ApplicationEventPublisherAware {
    private static ApplicationContext applicationContext;
    private static ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationHolder.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        ApplicationHolder.applicationEventPublisher = applicationEventPublisher;
    }

    public static ApplicationContext getApplicationContext() {
        return ApplicationHolder.applicationContext;
    }

    public static ApplicationEventPublisher getApplicationEventPublisher() {
        return applicationEventPublisher;
    }

}
