package common.pageObjects;

import common.utils.WebDriverWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.awt.print.PageFormat;

public class BasePage {

    protected WebDriver driver;

    protected WebDriverWrapper driverWrapper;

    public BasePage(){
    }

    public void setDriver(WebDriverWrapper driverWrapper) {
        this.driverWrapper = driverWrapper;
        this.driver = driverWrapper.getDriver();
        PageFactory.initElements(driver, this);
    }
}
