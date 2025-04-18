package com.minis.test;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/18 上午10:56
 */

public class BaseBaseService {
    private AServiceImpl as;

    public AServiceImpl getAs() {
        return as;
    }

    public void setAs(AServiceImpl as) {
        this.as = as;
    }

    public BaseBaseService() {
    }

    public void sayHello() {
        System.out.println("Base Base Service says hello");

    }
}