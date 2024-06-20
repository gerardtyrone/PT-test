package common.pageObjects;

import org.openqa.selenium.By;

public class CommonPage extends BasePage {

    public void clickOnNavItemByText(String text) {
        String locator = "li#nav-" + text;
        driverWrapper.clickElement(driver.findElement(By.cssSelector(locator)));
    }

}
