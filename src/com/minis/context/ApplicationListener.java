package com.minis.context;

import java.util.EventListener;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/5/6 下午4:24
 */
public class ApplicationListener implements EventListener {
    void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }
}
