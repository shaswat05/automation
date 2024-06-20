package com.test.automation.allure;

import com.test.automation.pojo.rest_api_client.HTTPRequest;
import com.test.automation.pojo.rest_api_client.HTTPResponse;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;

import java.text.MessageFormat;

public class AllureReportUtils {

    public static void addCurl(HTTPRequest httpRequest) {
        Allure.addAttachment(
                MessageFormat.format("HTTP Request: {0} {1}", httpRequest.getHTTPMethod(), httpRequest.getCompleteUrl()),
                httpRequest.getCurl(true)
        );
    }

    public static void addHTTPResponse(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        Allure.addAttachment(
                MessageFormat.format("HTTP Request: {0} {1} {2}", httpResponse.getStatusCode(), httpRequest.getHTTPMethod(), httpRequest.getCompleteUrl()),
                httpResponse.getBodyAsString()
        );
    }

    public static void updateTestMethodNameAndDescription(String description) {
        updateTestMethodName(description);
        addTestCaseDescription(description);
    }

    public static void updateTestMethodName(String name) {
        AllureLifecycle allureLifecycle = Allure.getLifecycle();
        allureLifecycle.updateTestCase(tc -> tc.setName(name));
    }

    public static void addTestCaseDescription(String description) {
        Allure.description(description);
    }

    public static void addAttachment(String name, String content) {
        Allure.addAttachment(name, content);
    }

}
