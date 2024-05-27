package com.test.automation.services.testNg;

import com.test.automation.pojo.controller.RunTestXmlSuitePojo;
import com.test.automation.pojo.testng.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

@Service
public class TestNGService {

    private final Logger log = LoggerFactory.getLogger(TestNGService.class.getName());

    @Value("${testng.xml.suite.location}")
    String xmlSuiteLocation;

    @Async("AsyncExecutorToRunTest")
    public void runTestNgXML(TestSuite suite, String testId) {
        this.run(suite.toTestNgXmlSuite(), testId);
    }

    @Async("AsyncExecutorToRunTest")
    public void runTestNgXML(RunTestXmlSuitePojo runTestXmlSuitePojo, String testId) {
        XmlSuite suite = new XmlSuite();
        String pathToXml = xmlSuiteLocation + "/" + runTestXmlSuitePojo.getFile();
        log.debug("XmlSuite file path: {}", pathToXml);
        suite.setSuiteFiles(Collections.singletonList(pathToXml));
        this.run(suite, testId);
    }


    public List<String> getAllXmlSuiteFiles() {
        String folderPath = xmlSuiteLocation;
        log.info(xmlSuiteLocation);
        try {
            return getAllXmlSuiteFiles(folderPath, folderPath, new ArrayList<>());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllXmlSuiteFiles(String folderPath, String parentFolderPath, List<String> xmlFiles) throws IOException {
        File dir = new File(folderPath);
        if (dir.isFile()) {
            final String fileName = dir.getCanonicalPath();
            if (fileName.contains(".xml")) xmlFiles.add(fileName.split(parentFolderPath + "/")[1]);
        } else if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) for (File file : files)
                xmlFiles = getAllXmlSuiteFiles(file.getAbsolutePath(), parentFolderPath, xmlFiles);
        }
        return xmlFiles;
    }

    private void run(XmlSuite suite, String testId) {
        String name = (suite.getName() != null) && (!suite.getName().isEmpty()) ? suite.getName() : "test";
        System.setProperty("allure.results.directory", "allure-results/" + name + "_" + testId);
        log.info("TestId: {}", testId);
        TestNG testNG = new TestNG();
        testNG.setXmlSuites(Collections.singletonList(suite));
        testNG.run();
    }


}