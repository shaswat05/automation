package com.test.automation.test;

import com.test.automation.helpers.web.amazon.HomePage;
import com.test.automation.helpers.web.amazon.ProductPage;
import com.test.automation.helpers.web.amazon.SearchResultPage;
import com.test.automation.web.BrowserName;
import com.test.automation.web.WebDriverFactory;
import com.test.automation.web.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebDriverTest {

    private final String URL = "https://www.amazon.in/";

    private WebDriver driver;

    @Test
    public void testWebDriver() throws InterruptedException {
        driver = WebDriverFactory.getWebDriver(BrowserName.CHROME_DRIVER);
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        HomePage homePage = new HomePage(driver);
        driver.manage().window().fullscreen();
        homePage.enterSearchText("pen");
        homePage.clickOnSearchButton();
        driver.manage().window().fullscreen();
        SearchResultPage searchResultPage = new SearchResultPage(driver);

        searchResultPage.clickOnFirstSearchResult();
        WebDriverUtils.switchToSecondWindowTab(driver);

        driver.navigate().refresh();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectFirstVariation();
        driver.manage().window().maximize();

//        Thread.sleep(5000);
//        productPage.selectProductVariation(2);
//        Thread.sleep(5000);
//        productPage.clickOnBuyNowButton();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
