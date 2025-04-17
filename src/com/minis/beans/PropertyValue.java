package com.minis.beans;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/17 上午9:06
 */
public class PropertyValue {

    private String type;

    private String name;

    private Object value;

    public PropertyValue(String type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
