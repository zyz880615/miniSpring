package com.minis.beans;

import com.minis.core.Resource;
import org.dom4j.Element;

import java.util.List;

public class XmlBeanDefinitionReader {
    SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);

            //处理属性
            List<Element> propertyElements = element.elements("property");

            PropertyValues propertyValues = new PropertyValues();

            for (Element e : propertyElements) {
                String pType = e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                propertyValues.addPropertyValue(new PropertyValue(pType, pName, pValue));
            }

            beanDefinition.setPropertyValues(propertyValues);

            List<Element> constructorElements = element.elements("constructor-arg");
            ArgumentValues argumentValues = new ArgumentValues();
            for (Element e : constructorElements) {
                String aType = e.attributeValue("type");
                String aName = e.attributeValue("name");
                String aValue = e.attributeValue("value");
                argumentValues.addArgumentValue(new ArgumentValue(aType, aName, aValue));
            }

            beanDefinition.setConstructorArgumentValues(argumentValues);

            this.simpleBeanFactory.registerBeanDefinition(beanID, beanDefinition);
        }
    }
}
