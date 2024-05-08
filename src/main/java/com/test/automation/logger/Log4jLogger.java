package com.test.automation.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class Log4jLogger {

    Logger logger = LogManager.getLogger();
    Logger logger2 = LogManager.getLogger(this.getClass());

    @Test
    public void f() {
        logger.log(Level.INFO, "Hello world!");
        logger2.log(Level.INFO, "Hello world!");
    }



}
