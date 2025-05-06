package com.minis.core.env;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/5/6 上午11:05
 */
public interface PropertyResolver {
    
    boolean containsProperty(String key);

    String getProperty(String key);

    String getProperty(String key, String defaultValue);

    <T> T getProperty(String key, Class<T> targetType);

    <T> T getProperty(String key, Class<T> targetType, T defaultValue);

    <T> Class<T> getPropertyAsClass(String key, Class<T> targetType);

    String getRequiredProperty(String key) throws IllegalStateException;

    <T> T getRequiredProperty(String key, Class<T> targetType) throws
            IllegalStateException;

    String resolvePlaceholders(String text);

    String resolveRequiredPlaceholders(String text) throws
            IllegalArgumentException;
}
