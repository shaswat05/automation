package com.test.automation.listeners;

import com.test.automation.utils.CommandLineUtilsTest;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;

public class AllureReportListener implements ITestNGListener, ITestListener{


    @Override
    public void onFinish(ITestContext context) {
        ISuite suite = context.getSuite();
        String testId = suite.getParameter("testId");
        String reportDirectory = suite.getParameter("reportDirectory");
        System.out.println("#####" + testId);
        String dir = System.getProperty("user.dir") + '/' + reportDirectory;
        CommandLineUtilsTest.run(dir, "allure generate --clean --single-file");
    }

}

