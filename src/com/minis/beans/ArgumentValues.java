package com.minis.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/17 上午9:07
 */
public class ArgumentValues {

    private final List<ArgumentValue> argumentValueList = new ArrayList<>();

    public ArgumentValues() {

    }

    public void addArgumentValue(ArgumentValue newValue) {
        this.argumentValueList.add(newValue);
    }

    public ArgumentValue getIndexedArgumentValue(int index) {
        return this.argumentValueList.get(index);
    }

    public int getArgumentCount() {
        return this.argumentValueList.size();
    }

    public boolean isEmpty() {
        return this.argumentValueList.isEmpty();
    }

}
