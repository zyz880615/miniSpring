package com.minis.context;

import java.util.EventObject;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/15 下午5:44
 */
public class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    protected String msg = null;

    public ApplicationEvent(Object arg0) {
        super(arg0);
        this.msg = arg0.toString();
    }
}
