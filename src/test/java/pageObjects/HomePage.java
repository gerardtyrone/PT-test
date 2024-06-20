package pageObjects;

import common.pageObjects.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//body[@ng-app='jupiterApp']")
    public WebElement bodyHomepage;

    public void isHomepageVisible() {
        driverWrapper.isElementVisible(bodyHomepage);
    }


}
