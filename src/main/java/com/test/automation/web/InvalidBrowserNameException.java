package com.test.automation.web;

public class InvalidBrowserNameException extends RuntimeException {

    public InvalidBrowserNameException(BrowserName browser) {
        super("WebDriver implementation not found for " + browser);
    }
}
