package com.test.automation.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Log4jLogger implements ILogger {

    private final Logger logger;

    Log4jLogger(String name) {
        logger = LogManager.getLogger(name);
    }

    @Override
    public void log(String message) {
        logger.info(message);
    }
}
