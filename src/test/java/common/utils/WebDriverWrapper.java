package common.utils;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverWrapper {

    private final WebDriver driver;

    public WebDriverWrapper(WebDriver driver) { this.driver = driver; }

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Description: Load a new web page in the current browser window
     *
     * @param url target page URL
     */
    public void get(String url) {
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.get(url);
        } catch (TimeoutException e) {
            Assert.fail("Page Load Timeout occurred: " + e);
        } catch (UnreachableBrowserException e) {
            Assert.fail("Unreachable Browser Exception occurred: " + e);
        } catch (Exception e) {
            Assert.fail("Exception: " + e);
        }
    }

    /**
     * Description: Check and wait until a specific element is located using
     * ExpectedConditions.visibilityOf(element)
     *
     * @param webElement element
     */
    public void isElementVisible(WebElement webElement) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            (new WebDriverWait(driver, Duration.ofSeconds(3))).until(ExpectedConditions.visibilityOf(webElement));
        } catch (TimeoutException e) {
            Assert.fail("Expected condition failed after " + 3 + " seconds waiting time: " + e);
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    /**
     * Description: Click a specified button
     *
     * @param element WebElement
     */
    public void clickElement(WebElement element) {
        (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * Description: Method to check if Element is NOT displayed on page
     *
     * @param elementBy By
     * @return boolean
     */
    public boolean isElementNotVisible(By elementBy) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            return driver.findElements(elementBy).isEmpty();
        } catch (TimeoutException e) {
            Assert.fail("Expected condition failed: Element is still visible");
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return false;
    }

    /**
     * Description: Wait for the invisibility of the element.
     *
     * @param webElement element
     */
    public void waitInvisibilityOfElement(WebElement webElement) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.invisibilityOf(webElement));
        } catch (TimeoutException e) {
            Assert.fail("Expected condition failed: Element is still visible");
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }
}
