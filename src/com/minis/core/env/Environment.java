package com.minis.core.env;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/5/6 上午11:06
 */
public interface Environment extends PropertyResolver {
    
    String[] getActiveProfiles();

    String[] getDefaultProfiles();

    boolean acceptsProfiles(String... profiles);
}
