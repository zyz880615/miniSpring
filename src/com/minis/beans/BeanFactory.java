package com.minis.beans;

/**
 * 提供获取Bean的接口
 *
 * @author zyz
 * @version 1.0
 * @date 2025/4/17 上午9:07
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    Boolean containsBean(String beanName);

    boolean isSingleton(String name);

    boolean isPrototype(String name);

    Class<?> getType(String name);
}
