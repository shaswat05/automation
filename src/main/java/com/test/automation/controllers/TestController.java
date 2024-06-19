package com.test.automation.controllers;

import com.test.automation.logger.ILogger;
import com.test.automation.logger.LoggerFactory;
import com.test.automation.pojo.controller.RunTestXmlSuitePojo;
import com.test.automation.pojo.testng.TestSuite;
import com.test.automation.services.testNg.TestNGService;
import com.test.automation.utils.JsonUtils;
import com.test.automation.utils.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class TestController {

    private final ILogger logger = LoggerFactory.getLogger(TestController.class.getName());

    @Autowired
    TestNGService service;

    @PostMapping("/custom/suite/run")
    public String runCustomTestSuite(@RequestBody TestSuite suite) throws ExecutionException, InterruptedException {
        logger.log("Request received: " + JsonUtils.pojoToJsonString(suite));
        String testId = RandomNumberGenerator.getTestId();
        service.runTestNgXML(suite, testId);
        String response = JsonUtils.mapToJson(Map.of("test_id", testId, "status", "success"));
        logger.log("Response: " + response);
        return response;
    }

    @PostMapping("/xml/suite/run")
    public String runTestXmlSuite(@RequestBody RunTestXmlSuitePojo runTestXmlSuitePojo) throws ExecutionException, InterruptedException {
        logger.log("Request received: " + JsonUtils.pojoToJsonString(runTestXmlSuitePojo));
        String testId = RandomNumberGenerator.getTestId();
        service.runTestNgXML(runTestXmlSuitePojo, testId);
        String response = JsonUtils.mapToJson(Map.of("test_id", testId, "status", "success"));
        logger.log("Response: " + response);
        return response;
    }

    @GetMapping("/xml/suite")
    public Map<String, List<String>> getAllXmlSuiteFiles() {
        logger.log("Request received to get all the xml suites path.");
        List<String> suiteFiles = service.getAllXmlSuiteFiles();
        Map<String, List<String>> responseMap = Map.of("xml_files", suiteFiles);
        logger.log("Response to get all the xml suites path: " + responseMap);
        return responseMap;
    }

}
