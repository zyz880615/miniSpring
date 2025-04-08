package com.minis.beans;

public interface BeanFactory {

    Object getBean(String beanId) throws BeansException;

    void registerBeanDefinition(BeanDefinition beanDefinition);
}
