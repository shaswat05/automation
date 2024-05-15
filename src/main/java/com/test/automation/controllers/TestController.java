package com.test.automation.controllers;

import com.test.automation.pojo.controller.RunTestXmlSuitePojo;
import com.test.automation.pojo.testng.TestSuite;
import com.test.automation.services.testNg.TestNGService;
import com.test.automation.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    private final Logger log = LoggerFactory.getLogger(TestController.class.getName());

    @Autowired
    TestNGService service;


    @PostMapping("/suite/run")
    public String runTestSuite(@RequestBody TestSuite suite) {
        log.info("Request received: " + JsonUtils.pojoToJsonString(suite));
        service.runTestNgXML(suite);
        return JsonUtils.mapToJson(Map.of("status", "success"));
    }

    @PostMapping("/xml/suite/run")
    public String runTestXmlSuite(@RequestBody RunTestXmlSuitePojo runTestXmlSuitePojo) {
        log.info("Request received: " + JsonUtils.pojoToJsonString(runTestXmlSuitePojo));
        service.runTestNgXML(runTestXmlSuitePojo);
        return JsonUtils.mapToJson(Map.of("status", "success"));
    }

    @GetMapping("/xml/suite")
    public Map<String, List<String>> getAllXmlSuiteFiles() {
        log.info("Request received to get all the xml suites path.");
        List<String> suiteFiles = service.getAllXmlSuiteFiles();
        Map<String, List<String>> responseMap = Map.of("xml_files", suiteFiles);
        log.info("Response to get all the xml suites path: {}", responseMap);
        return responseMap;
    }

}
