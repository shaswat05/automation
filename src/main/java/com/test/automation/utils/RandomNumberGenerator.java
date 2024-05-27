package com.test.automation.utils;

public class RandomNumberGenerator {

    private static long lastTestId = 0;

    public synchronized static String getTestId() {
        lastTestId = (lastTestId == 0) ? System.currentTimeMillis() : lastTestId + 1;
        return String.valueOf(lastTestId);
    }

}
