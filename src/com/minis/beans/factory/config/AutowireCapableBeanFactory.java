package com.minis.beans.factory.config;

import com.minis.beans.BeanFactory;
import com.minis.beans.BeansException;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/21 上午11:41
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    int AUTOWIRE_NO = 0;
    int AUTOWIRE_BY_NAME = 1;
    int AUTOWIRE_BY_TYPE = 2;

    Object applyBeanPostProcessorBeforeInitialization(Object existingBean,
                                                      String beanName) throws BeansException;

    Object applyBeanPostProcessorAfterInitialization(Object existingBean,
                                                     String beanName) throws BeansException;
}
