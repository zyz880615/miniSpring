package com.minis.beans.factory.config;

import com.minis.beans.BeanFactory;
import com.minis.beans.BeansException;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/5/7 上午9:14
 */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
