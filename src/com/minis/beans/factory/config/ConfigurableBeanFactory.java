package com.minis.beans.factory.config;

import com.minis.beans.BeanFactory;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/5/6 上午10:18
 */
public interface ConfigurableBeanFactory extends
        BeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    int getBeanPostProcessorCount();

    void registerDependentBean(String beanName, String dependentBeanName);

    String[] getDependentBeans(String beanName);

    String[] getDependenciesForBean(String beanName);
}