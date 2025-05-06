package com.minis.context;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/5/6 下午4:25
 */
public class SimpleApplicationEventPublisher implements
        ApplicationEventPublisher {
    List<ApplicationListener> listeners = new ArrayList<>();

    @Override
    public void publishEvent(ApplicationEvent event) {
        for (ApplicationListener listener : listeners) {
            listener.onApplicationEvent(event);
        }
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.listeners.add(listener);
    }
}
