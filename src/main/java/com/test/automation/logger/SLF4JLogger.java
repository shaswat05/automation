package com.test.automation.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SLF4JLogger implements ILogger {

    private final Logger logger;

    SLF4JLogger(String name) {
        logger = LoggerFactory.getLogger(name);
    }

    @Override
    public void log(String message) {
        logger.info(message);
    }

}
