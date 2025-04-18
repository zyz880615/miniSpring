package com.minis.test;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/18 上午10:57
 */
public class BaseService {
    private BaseBaseService bbs;

    public BaseBaseService getBbs() {
        return bbs;
    }

    public void setBbs(BaseBaseService bbs) {
        this.bbs = bbs;
    }

    public BaseService() {
    }

    public void sayHello() {
        System.out.print("Base Service says hello");
        bbs.sayHello();
    }
}