package com.minis.beans.factory.support;

import com.minis.beans.factory.config.SingletonBeanRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/15 下午5:26
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    //容器中存放所有bean的名称的列表
    protected List<String> beanNames = new ArrayList<>();

    //容器中存放所有bean实例的Map
    protected final Map<String, Object> singletons = new ConcurrentHashMap<>(256);

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletons) {
            this.singletons.put(beanName, singletonObject);
            this.beanNames.add(beanName);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletons.get(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return this.singletons.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return (String[]) this.beanNames.toArray();
    }

    protected void removeSingleton(String beanName) {
        synchronized (this.singletons) {
            this.beanNames.remove(beanName);
            this.singletons.remove(beanName);
        }
    }
}
