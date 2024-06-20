package com.test.automation.helpers.web.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {

    private final WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By productVariations = By.xpath("//div[@id='variation_size_name']/ul/li");

    private final By byNowButton = By.id("buy-now-button");

    public void selectFirstVariation() {
        if (isVariationPresent()) {
            driver.findElements(productVariations).getFirst().click();
        }
    }

    public void selectProductVariation(int variationPosition) {
        if (isVariationPresent()) {
            List<WebElement> webElements = driver.findElements(productVariations);
            if (variationPosition > 0 && variationPosition < webElements.size())
                webElements.get(variationPosition - 1).click();

        }
    }

    public void clickOnBuyNowButton() {
        driver.findElement(byNowButton).click();
    }

    private boolean isVariationPresent() {
        boolean isDisplayed = driver.findElement(productVariations).isDisplayed();
        if (!isDisplayed) return false;
        return !driver.findElements(productVariations).isEmpty();
    }


}
