package com.test.automation.helpers.web.amazon;

import com.test.automation.logger.ILogger;
import com.test.automation.logger.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {

    private final WebDriver driver;

    private final ILogger logger;

    public SignInPage(WebDriver driver) {
        logger = LoggerFactory.getLogger(SignInPage.class.getSimpleName());
        logger.log("Initializing Sign In page.");
        this.driver = driver;
    }

    private final By emailOrMobileNumber = By.name("email");
    private final By continueButton = By.id("continue");
    private final By password = By.name("password");

    private final By signInSubmitButton = By.id("signInSubmit");

    public void enterEmailOrMobileNumber(String emailOrMobileNumber) {
        logger.log("Enter email or mobile number: " + emailOrMobileNumber);
        driver.findElement(this.emailOrMobileNumber).sendKeys(emailOrMobileNumber);
    }

    public void clickOnContinueButton() {
        driver.findElement(continueButton).click();
    }

    public void enterPassword(String password) {
        logger.log("Enter password: " + emailOrMobileNumber);
        driver.findElement(this.password).sendKeys(password);
    }

    public void clickOnSignInSubmitButton() {
        driver.findElement(signInSubmitButton).click();
    }

}
