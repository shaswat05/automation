package com.test.automation.logger;

public class LoggerFactory {

    public static ILogger getLogger(String name) {
        return new SLF4JLogger(name);
    }

}
