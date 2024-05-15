package com.test.automation.helpers.web.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage {

    private final WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By searchResults = By.xpath("//span[@data-component-type='s-product-image']");

    public void clickOnFirstSearchResult() {
        getSearchResultElements().getFirst().click();
    }

    public int getSearchResultCount() {
        return getSearchResultElements().size();
    }

    private List<WebElement> getSearchResultElements() {
        return driver.findElements(searchResults);
    }

}
