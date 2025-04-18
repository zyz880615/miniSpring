package com.minis.beans.factory.config;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/15 下午5:20
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();
}
