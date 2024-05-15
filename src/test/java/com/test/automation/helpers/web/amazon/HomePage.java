package com.test.automation.helpers.web.amazon;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;
    private final Logger log = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        log.info("Initializing home page.");
        this.driver = driver;
    }

    private final By accountAndLists = By.partialLinkText("Account & Lists");
    private final By searchField = By.id("twotabsearchtextbox");
    private final By searchButton = By.id("nav-search-submit-button");

    public void accountsAndListsLink() {
        log.info("Click on 'Account & Lists'.");
        driver.findElement(accountAndLists).click();
    }

    public void enterSearchText(String searchText) {
        driver.findElement(searchField).sendKeys(searchText);
    }

    public void clickOnSearchButton() {
        driver.findElement(searchButton).click();
    }

}
