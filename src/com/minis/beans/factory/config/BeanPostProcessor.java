package com.minis.beans.factory.config;

import com.minis.beans.BeanFactory;
import com.minis.beans.BeansException;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/18 上午11:06
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

    void setBeanFactory(BeanFactory beanFactory);
}
