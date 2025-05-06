package com.minis.context;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/15 下午5:44
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);

    void addApplicationListener(ApplicationListener listener);
}
