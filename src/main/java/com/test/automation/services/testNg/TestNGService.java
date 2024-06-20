package com.test.automation.services.testNg;

import com.test.automation.listeners.AllureReportListener;
import com.test.automation.logger.ILogger;
import com.test.automation.logger.LoggerFactory;
import com.test.automation.pojo.controller.RunTestXmlSuitePojo;
import com.test.automation.pojo.testng.TestSuite;
import com.test.automation.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TestNGService {

    private final ILogger logger = LoggerFactory.getLogger(TestNGService.class.getName());

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
        logger.log("XmlSuite file path: " + pathToXml);
        suite.setName(getReportDir(runTestXmlSuitePojo.getFile()));
        suite.setSuiteFiles(Collections.singletonList(pathToXml));
        logger.log("XmlSuite file path: " + suite.getName());
        this.run(suite, testId);
    }


    public List<String> getAllXmlSuiteFiles() {
        String folderPath = xmlSuiteLocation;
        logger.log(xmlSuiteLocation);
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
        String reportDir = getReportDir(testId);
        System.setProperty("allure.results.directory", reportDir + "/allure-results");
        logger.log("suite: " + JsonUtils.pojoToJsonString(suite));
        logger.log("TestId: " + testId);
        TestNG testNG = new TestNG();
        testNG.setXmlSuites(Collections.singletonList(suite));
        testNG.addListener(new AllureReportListener(testId, reportDir));
        testNG.run();
    }

    private String getReportDir(String testId) {
        return "reports/" + "test" + "_" + testId;
    }

    public String getReportLink(String testId) {
        return System.getProperty("user.dir") + '/' + getReportDir(testId) + "/allure-report/index.html";
    }

    public String getReport(String testId) throws Exception {
        String uri = getReportLink(testId);
        File file = new File(uri);
        file.setReadable(true);
        StringBuilder html = new StringBuilder();
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        String val;
        while ((val = br.readLine()) != null) html.append(val);
        br.close();
        return html.toString();
    }

}
