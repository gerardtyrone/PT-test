package pageObjects;

import common.pageObjects.BasePage;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;
import java.util.Map;

public class ContactPage extends BasePage {

    @FindBy(xpath = "//div[contains(@ui-if,'contact')]")
    WebElement contactPageBody;

    @FindBy(css = "a.btn-contact")
    WebElement submitBtn;

    @FindBy(id = "forename")
    WebElement forenameInput;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "message")
    WebElement messageFld;

    @FindBy(css = "div.alert-success")
    WebElement successMsg;

    @FindBy(css = "div.popup div.modal-body")
    WebElement popupLoadingBar;

    public void isContactPageVisible() {
        driverWrapper.isElementVisible(contactPageBody);
    }

    public void clickSubmit() {
        driverWrapper.clickElement(submitBtn);
    }

    public void validateErrorMsgs(DataTable dataTable) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : table) {
            String elem = row.get("Element");
            String expectedMsg = row.get("Error Message");
            By locator = getLocatorForErrorElements(elem);
            WebElement errorElem = driver.findElement(locator);
            String actualMsg = errorElem.getText().trim();

            Assert.assertTrue("Error message mismatch for element: " + elem +
                            "\nExpected: " + expectedMsg +
                            "\nActual: " + actualMsg,
                    actualMsg.contains(expectedMsg));

        }
    }

    private By getLocatorForErrorElements(String element) {
        return switch (element) {
            case "Header" -> By.cssSelector("div#header-message div.alert");
            case "Forename" -> By.id("forename-err");
            case "Email" -> By.id("email-err");
            case "Message" -> By.id("message-err");
            default -> throw new IllegalArgumentException("Unknown element: " + element);
        };
    }

    public void enterForename(String name) {
        forenameInput.sendKeys(name);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterMessage(String msg) {
        messageFld.sendKeys(msg);
    }

    public void validateErrorElements() {
        String hdrMSg = driver.findElement(getLocatorForErrorElements("Header")).getText();
        Assert.assertFalse("Header still contains warnings: " + hdrMSg, hdrMSg.contains("complete the form correctly"));
        Assert.assertTrue("Forename error message is still displayed", driverWrapper.isElementNotVisible(getLocatorForErrorElements("Forename")));
        Assert.assertTrue("Email error message is still displayed", driverWrapper.isElementNotVisible(getLocatorForErrorElements("Email")));
        Assert.assertTrue("Message error message is still displayed", driverWrapper.isElementNotVisible(getLocatorForErrorElements("Message")));
    }

    public void validateSuccessMsg(String forename) {
        driverWrapper.waitInvisibilityOfElement(popupLoadingBar);
        String actualMsg = successMsg.getText().trim();
        Assert.assertTrue("Success Message doesn't contain the correct forename: " + forename, actualMsg.contains(forename));
    }
}
