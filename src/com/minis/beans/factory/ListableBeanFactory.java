package com.minis.beans.factory;

import com.minis.beans.BeanFactory;
import com.minis.beans.BeansException;

import java.util.Map;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/25 上午10:38
 */
public interface ListableBeanFactory extends BeanFactory {

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

    String[] getBeanNamesForType(Class<?> type);

    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;
}
