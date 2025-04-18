package com.minis.beans.factory.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/17 上午9:07
 */
public class ConstructorArgumentValues {

    private final List<ConstructorArgumentValue> argumentValueList = new ArrayList<>();

    public ConstructorArgumentValues() {

    }

    public void addArgumentValue(ConstructorArgumentValue newValue) {
        this.argumentValueList.add(newValue);
    }

    public ConstructorArgumentValue getIndexedArgumentValue(int index) {
        return this.argumentValueList.get(index);
    }

    public int getArgumentCount() {
        return this.argumentValueList.size();
    }

    public boolean isEmpty() {
        return this.argumentValueList.isEmpty();
    }

}
