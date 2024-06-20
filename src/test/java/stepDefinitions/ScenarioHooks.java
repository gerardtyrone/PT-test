package stepDefinitions;

import common.browserHelper.BrowserFactory;
import common.utils.WebDriverWrapper;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;

public class ScenarioHooks implements En {

    public WebDriverWrapper driverWrapper;

    public ScenarioHooks(HomePage homePage) {

        Before((Scenario scenario) -> {
            if (driverWrapper == null) {
                WebDriver driver = BrowserFactory.getBrowser(scenario);
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                driverWrapper = new WebDriverWrapper(driver);
            }
        });

        After((Scenario scenario) -> {
            driverWrapper.getDriver().quit();
            driverWrapper = null;
        });
    }

    public WebDriverWrapper getDriverWrapper() { return driverWrapper; }

}
