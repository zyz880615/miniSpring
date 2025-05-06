package com.minis.context;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/5/6 下午4:24
 */
public class ContextRefreshEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;

    public ContextRefreshEvent(Object arg0) {
        super(arg0);
    }

    public String toString() {
        return this.msg;
    }
}
