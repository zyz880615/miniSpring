package com.minis.beans.factory.config;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/5/6 上午10:19
 */

import com.minis.beans.factory.ListableBeanFactory;

public interface ConfigurableListableBeanFactory
        extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
}
