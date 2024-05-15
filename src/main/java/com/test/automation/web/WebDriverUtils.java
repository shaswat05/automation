package com.test.automation.web;

import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;

public class WebDriverUtils {


    public static void closeAllTabs(WebDriver driver) {
        driver.quit();
    }

    public static void closeCurrentTab(WebDriver driver) {
        driver.close();
    }

    public static void switchToParentWindowTab(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public static void switchToSecondWindowTab(WebDriver driver) {
        Set<String> windowHandles = getWindowHandles(driver);
        if (windowHandles.size() > 1) {
            Iterator<String> itr = windowHandles.iterator();
            itr.next();
            driver.switchTo().window(itr.next());
        }
    }

    public static String getWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public static Set<String> getWindowHandles(WebDriver driver) {
        driver.switchTo().parentFrame();
        return driver.getWindowHandles();
    }

}
