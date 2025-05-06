package com.minis.beans.factory.support;

import com.minis.beans.BeanFactory;
import com.minis.beans.BeansException;
import com.minis.beans.PropertyValue;
import com.minis.beans.PropertyValues;
import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.factory.config.ConstructorArgumentValue;
import com.minis.beans.factory.config.ConstructorArgumentValues;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zyz
 * @version 1.0
 * @date 2025/4/21 上午10:43
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    protected List<String> beanDefinitionNames = new ArrayList<>();

    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);

    public AbstractBeanFactory() {

    }

    public void refresh() {
        for (String beanName : beanDefinitionNames) {
            try {
                getBean(beanName);
            } catch (Exception e) {

            }
        }
    }

    @Override
    //getBean，容器的核心方法
    public Object getBean(String beanName) throws BeansException {
        //先尝试直接拿Bean实例
        Object singleton = this.getSingleton(beanName);
        if (singleton == null) {
            //如果没有实例，则尝试从毛坯中获取
            singleton = this.earlySingletonObjects.get(beanName);

            if (singleton == null) {
                //如果连毛坯都没有，则创建bean实例并注册
                BeanDefinition beanDefinition = this.getBeanDefinition(beanName);
                singleton = createBean(beanDefinition);
                this.registerBean(beanName, singleton);
                // 预留beanpostprocessor位置
                // step 1: postProcessBeforeInitialization
                applyBeanPostProcessorBeforeInitialization(singleton, beanName);
                // step 2: init-method
                if (beanDefinition.getInitMethodName() != null && !beanDefinition.equals("")) {
                    invokeInitMethod(beanDefinition, singleton);
                }
                // step 3: postProcessAfterInitialization
                applyBeanPostProcessorAfterInitialization(singleton, beanName);
            }
        }
        return singleton;
    }

    private void invokeInitMethod(BeanDefinition beanDefinition, Object obj) {
        Class<?> clz = beanDefinition.getClass();

        Method method;

        try {
            method = clz.getMethod(beanDefinition.getInitMethodName());
            method.invoke(obj);
        } catch (Exception e) {

        }
    }

    abstract public Object applyBeanPostProcessorAfterInitialization(Object singleton, String beanName) throws BeansException;

    abstract public Object applyBeanPostProcessorBeforeInitialization(Object singleton, String beanName) throws BeansException;

    private Object createBean(BeanDefinition beanDefinition) {
        Class<?> clz = null;
        //创建毛坯bean实例
        Object obj = doCreateBean(beanDefinition);
        this.earlySingletonObjects.put(beanDefinition.getId(), obj);
        try {
            clz = Class.forName(beanDefinition.getClassName());
        } catch (Exception e) {

        }
        //完善bean，主要是处理属性
        populateBean(beanDefinition, clz, obj);
        return obj;
    }

    private void populateBean(BeanDefinition beanDefinition, Class<?> clz, Object obj) {
        handleProperties(beanDefinition, clz, obj);
    }

    //doCreateBean创建毛胚实例，仅仅调用构造方法，没有进行属性处理
    private Object doCreateBean(BeanDefinition beanDefinition) {
        Class<?> clz;
        Object obj = null;
        Constructor con;
        try {
            clz = Class.forName(beanDefinition.getClassName());
            // 处理构造器参数
            ConstructorArgumentValues argumentValues =
                    beanDefinition.getConstructorArgumentValues();
            //如果有参数
            if (!argumentValues.isEmpty()) {
                Class<?>[] paramTypes = new Class<?>
                        [argumentValues.getArgumentCount()];
                Object[] paramValues = new
                        Object[argumentValues.getArgumentCount()];
                //对每一个参数，分数据类型分别处理
                for (int i = 0; i < argumentValues.getArgumentCount(); i++) {
                    ConstructorArgumentValue argumentValue =
                            argumentValues.getIndexedArgumentValue(i);
                    if ("String".equals(argumentValue.getType()) ||
                            "java.lang.String".equals(argumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    } else if ("Integer".equals(argumentValue.getType()) ||
                            "java.lang.Integer".equals(argumentValue.getType())) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] =
                                Integer.valueOf((String) argumentValue.getValue());
                    } else if ("int".equals(argumentValue.getType())) {
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String)
                                argumentValue.getValue());
                    } else { //默认为string
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }
                }
                try {
                    //按照特定构造器创建实例
                    con = clz.getConstructor(paramTypes);
                    obj = con.newInstance(paramValues);
                } catch (Exception e) {

                }
            } else { //如果没有参数，直接创建实例
                obj = clz.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {

        }
        System.out.println(beanDefinition.getId() + " bean created. " + beanDefinition.getClassName() + " : " + obj.toString());
        return obj;
    }

    private void handleProperties(BeanDefinition beanDefinition, Class<?> clz, Object obj) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (!propertyValues.isEmpty()) {
            for (int i = 0; i < propertyValues.size(); i++) {
                //对每一个属性，分数据类型分别处理
                PropertyValue propertyValue =
                        propertyValues.getPropertyValueList().get(i);
                String pType = propertyValue.getType();
                String pName = propertyValue.getName();
                Object pValue = propertyValue.getValue();
                boolean isRef = propertyValue.getRef();
                Class<?>[] paramTypes = new Class<?>[1];
                Object[] paramValues = new Object[1];
                if (!isRef) {   //普通属性处理逻辑
                    if ("String".equals(pType) || "java.lang.String".equals(pType)) {
                        paramTypes[0] = String.class;
                    } else if ("Integer".equals(pType) ||
                            "java.lang.Integer".equals(pType)) {
                        paramTypes[0] = Integer.class;
                    } else if ("int".equals(pType)) {
                        paramTypes[0] = int.class;
                    } else { // 默认为string
                        paramTypes[0] = String.class;
                    }
                    paramValues[0] = pValue;
                } else {    //Ref属性处理逻辑
                    try {
                        paramTypes[0] = Class.forName(pType);
                        paramValues[0] = getBean((String) pValue);
                    } catch (Exception e) {

                    }
                }
                //按照setXxxx规范查找setter方法，调用setter方法设置属性
                String methodName = "set" + pName.substring(0, 1).toUpperCase()
                        + pName.substring(1);
                Method method = null;
                try {
                    method = clz.getMethod(methodName, paramTypes);
                } catch (Exception e) {

                }
                try {
                    method.invoke(obj, paramValues);
                } catch (Exception e) {

                }
            }
        }
    }

    private void registerBean(String beanName, Object bean) {
        this.registerSingleton(beanName, bean);
    }

    @Override
    public Boolean containsBean(String beanName) {
        return this.beanDefinitionNames.contains(beanName);
    }

    @Override
    public boolean isSingleton(String name) {
        return this.beanDefinitionMap.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return this.beanDefinitionMap.get(name).isProtoType();
    }

    @Override
    public Class<?> getType(String name) {
        return null;
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
        if (!beanDefinition.isLazyInit()) {
            try {
                getBean(name);
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }
}
