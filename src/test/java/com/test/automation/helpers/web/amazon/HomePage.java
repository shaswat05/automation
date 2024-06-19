package com.test.automation.helpers.web.amazon;

import com.test.automation.logger.ILogger;
import com.test.automation.logger.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;
    private final ILogger logger;

    public HomePage(WebDriver driver) {
        logger = LoggerFactory.getLogger(HomePage.class.getSimpleName());
        logger.log("Initializing home page.");
        this.driver = driver;
    }

    private final By accountAndLists = By.partialLinkText("Account & Lists");
    private final By searchField = By.id("twotabsearchtextbox");
    private final By searchButton = By.id("nav-search-submit-button");

    public void accountsAndListsLink() {
        logger.log("Click on 'Account & Lists'.");
        driver.findElement(accountAndLists).click();
    }

    public void enterSearchText(String searchText) {
        driver.findElement(searchField).sendKeys(searchText);
    }

    public void clickOnSearchButton() {
        driver.findElement(searchButton).click();
    }

}
