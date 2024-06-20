package common.browserHelper;

import io.cucumber.java8.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BrowserFactory {

    private BrowserFactory() {
        // Throw an exception if constructor is called
        throw new AssertionError("Instantiating utility class.");
    }

    public static WebDriver getBrowser(Scenario scenario) {
        WebDriver driver = getWebDriver(scenario);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver getWebDriver(Scenario scenario) {
        WebDriver driver;
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
