package com.test.automation.pojo.testng;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Test {

    String name;
    int verbose = 2;

    @JsonProperty("preserve-order")
    String preserveOrder = "true";

    @JsonProperty("classes")
    List<TestClass> testClasses;

    public XmlTest toTestNgXmltest(XmlSuite suite) {
        XmlTest test = new XmlTest();
        test.setSuite(suite);
        test.setName(this.name);
        test.setVerbose(this.verbose);
        test.setClasses(testClasses.stream().map(TestClass::toTestNgXmlClass).collect(Collectors.toList()));
        return test;
    }


}
