package com.test.automation.utils.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;

public class AllureReportListener implements ITestListener {


    @Override
    public void onFinish(ITestContext context) {
        String testId = context.getAttribute("").toString();

    }

}

