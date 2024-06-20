package com.test.automation.listeners;

import com.test.automation.utils.CommandLineUtilsTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;

public class AllureReportListener implements ITestNGListener, ITestListener {

    private final String testId;
    private final String reportDirectory;

    public AllureReportListener(String testId, String reportDirectory) {
        this.testId = testId;
        this.reportDirectory = reportDirectory;
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("#####" + testId);
        String dir = System.getProperty("user.dir") + '/' + reportDirectory;
        CommandLineUtilsTest.run(dir, "allure generate --clean --single-file");
    }

}

