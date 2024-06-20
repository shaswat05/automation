package com.test.automation.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Arrays;

public class WebDriverFactory {

    public static WebDriver getWebDriver(BrowserName browser) {
        return switch (browser) {
            case CHROME_DRIVER -> getChromeDriver();
            case FIREFOX_DRIVER -> new FirefoxDriver();
            case SAFARI_DRIVER -> new SafariDriver();
            case EDGE_DRIVER -> new EdgeDriver();
            default -> throw new InvalidBrowserNameException(browser);
        };
    }

    private static ChromeDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        return new ChromeDriver(options);
    }

}
