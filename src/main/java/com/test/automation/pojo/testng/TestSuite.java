package com.test.automation.pojo.testng;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.stream.Collectors;


@Data
@JsonPropertyOrder({"name", "parallel", "threadCount"})
public class TestSuite {

    @JsonProperty("name")
    String name;
    @JsonProperty("parallel")
    String parallel;
    @JsonProperty("thread-count")
    int threadCount;

    @JsonProperty("test")
    List<Test> tests;

    public XmlSuite toTestNgXmlSuite() {
        XmlSuite suite = new XmlSuite();
        suite.setName(this.name);
        suite.setParallel(XmlSuite.ParallelMode.getValidParallel(parallel));
        suite.setThreadCount(threadCount);
        for (Test test : tests) suite.addTest(test.toTestNgXmltest(suite));
        return suite;
    }
}
