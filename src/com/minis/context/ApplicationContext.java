package com.minis.context;

import com.minis.beans.BeansException;
import com.minis.beans.factory.ListableBeanFactory;
import com.minis.beans.factory.config.ConfigurableBeanFactory;
import com.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.minis.core.env.Environment;
import com.minis.core.env.EnvironmentCapable;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/5/6 下午4:26
 */
public interface ApplicationContext
        extends EnvironmentCapable, ListableBeanFactory,
        ConfigurableBeanFactory, ApplicationEventPublisher {
    String getApplicationName();

    long getStartupDate();

    ConfigurableListableBeanFactory getBeanFactory() throws
            IllegalStateException;

    void setEnvironment(Environment environment);

    Environment getEnvironment();

    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);

    void refresh() throws BeansException, IllegalStateException;

    void close();

    boolean isActive();
}
