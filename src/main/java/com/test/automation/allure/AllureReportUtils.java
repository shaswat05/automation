package com.test.automation.allure;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;

public class AllureReportUtils {

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
