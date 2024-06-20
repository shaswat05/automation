package com.test.automation.pojo.testng;

import lombok.Data;
import org.testng.xml.XmlClass;

@Data
public class TestClass {

    String name;

    public XmlClass toTestNgXmlClass() {
        XmlClass xmlClass = new XmlClass();
        xmlClass.setClass(getClassFromClassPath(this.name));
        xmlClass.setName(this.name);
        return xmlClass;
    }

    private Class<?> getClassFromClassPath(String classPath) {
        try {
            return Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
