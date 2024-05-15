package com.test.automation.services.testNg;

import com.test.automation.pojo.controller.RunTestXmlSuitePojo;
import com.test.automation.pojo.testng.TestSuite;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TestNGService {

    private final Logger log = LogManager.getLogger(TestNGService.class.getName());

    @Value("${testng.xml.suite.location}")
    String xmlSuiteLocation;

    public void runTestNgXML(TestSuite suite) {
        TestNG testNG = new TestNG();
        testNG.setXmlSuites(Collections.singletonList(suite.toTestNgXmlSuite()));
        testNG.run();
    }

    public void runTestNgXML(RunTestXmlSuitePojo runTestXmlSuitePojo) {
        XmlSuite suite = new XmlSuite();
        String pathToXml = "./src/test/resources/testng_xml/" + runTestXmlSuitePojo.getFile();
        log.debug("XmlSuite file path: " + pathToXml);
        suite.setSuiteFiles(Collections.singletonList(pathToXml));
        TestNG testNG = new TestNG();
        testNG.setXmlSuites(Collections.singletonList(suite));
        testNG.run();
    }


    public List<String> getAllXmlSuiteFiles() {
//        String folderPath = "./src/test/resources/testng_xml";
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

}
