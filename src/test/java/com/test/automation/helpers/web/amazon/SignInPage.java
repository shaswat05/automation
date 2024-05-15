package com.test.automation.helpers.web.amazon;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {

    private final WebDriver driver;

    private final Logger log = LogManager.getLogger(SignInPage.class);

    public SignInPage(WebDriver driver) {
        log.info("Initializing Sign In page.");
        this.driver = driver;
    }

    private final By emailOrMobileNumber = By.name("email");
    private final By continueButton = By.id("continue");
    private final By password = By.name("password");

    private final By signInSubmitButton = By.id("signInSubmit");

    public void enterEmailOrMobileNumber(String emailOrMobileNumber) {
        log.info("Enter email or mobile number: " + emailOrMobileNumber);
        driver.findElement(this.emailOrMobileNumber).sendKeys(emailOrMobileNumber);
    }

    public void clickOnContinueButton() {
        driver.findElement(continueButton).click();
    }

    public void enterPassword(String password) {
        log.info("Enter password: " + emailOrMobileNumber);
        driver.findElement(this.password).sendKeys(password);
    }

    public void clickOnSignInSubmitButton() {
        driver.findElement(signInSubmitButton).click();
    }

}
