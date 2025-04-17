package com.minis.beans;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/17 上午9:07
 */
public class ArgumentValues {

    private final Map<Integer, ArgumentValue> indexedArgumentValues = new HashMap<>(0);

    private final List<ArgumentValue> genericArgumentValues = new LinkedList<>();

    public ArgumentValues() {

    }

    private void addArgumentValue(Integer key, ArgumentValue newValue) {
        this.indexedArgumentValues.put(key, newValue);
    }

    public boolean hasIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.containsKey(index);
    }

    public ArgumentValue getIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.get(index);
    }

    public void addGenericArgumentValue(String type, String name, Object value) {
        this.genericArgumentValues.add(new ArgumentValue(type, name, value));
    }

    private void addGenericArgumentValue(ArgumentValue newValue) {
        if (newValue.getName() != null) {
            this.genericArgumentValues.removeIf(currentValue -> newValue.getName().equals(currentValue.getName()));
        }
        this.genericArgumentValues.add(newValue);
    }

    public ArgumentValue getGenericArgumentValues(String requiredName) {
        for (ArgumentValue valueHolder : this.genericArgumentValues) {
            if (valueHolder.getName() != null && (!valueHolder.getName().equals(requiredName))) {
                continue;
            }
            return valueHolder;
        }
        return null;
    }
    
    public int getArgumentCount() {
        return this.genericArgumentValues.size();
    }

    public boolean isEmpty() {
        return this.genericArgumentValues.isEmpty();
    }

}
