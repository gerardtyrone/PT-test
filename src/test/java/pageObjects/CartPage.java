package pageObjects;

import common.pageObjects.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.Map;

public class CartPage extends BasePage {

    @FindBy(xpath = "//div[contains(@ui-if,'cart')]")
    WebElement cartPageBody;

    @FindBys(value = @FindBy(css = "table.cart-items  tr.cart-item"))
    private List<WebElement> cartItems;


    @FindBys(value = @FindBy(css = "table.cart-items thead th"))
    private List<WebElement> tableHdrs;

    @FindBy(css = "[class^='total']")
    WebElement cartTotal;

    By qtyInput = By.cssSelector("input[name='quantity']");

    public void isCartPageVisible() {
        driverWrapper.isElementVisible(cartPageBody);
    }

    public void checkItemAndPrice(String item, String expectedPrice){
        for (WebElement elem : cartItems) {
            String currentItem = elem.findElement(By.cssSelector(getCssPathFromTblHr("Item"))).getText();
            if (item.equals(currentItem)){
                String actualPrice = elem.findElement(By.cssSelector(getCssPathFromTblHr("Price"))).getText();
                Assert.assertEquals("Incorrect price (" + actualPrice + ") is shown in cart for item: " + item, "$"+expectedPrice, actualPrice);
            }
        }
    }

    public String getCssPathFromTblHr(String expectedHdr) {
        return "td:nth-child(" + determineColumnNum(expectedHdr) + ")";
    }

    private int determineColumnNum(String expectedHdr) {
        for (int i = 0; i < tableHdrs.size(); i++) {
            if (expectedHdr.equals(tableHdrs.get(i).getText().trim())) {
                return i + 1;
            }
        }
        return 0;
    }

    public void checkSubtotalOfEachItem(){
        for (WebElement elem : cartItems) {
            double itemPrice = Double.parseDouble(elem.findElement(By.cssSelector(getCssPathFromTblHr("Price"))).getText().replaceAll("[^\\d.]", ""));
            int qty = Integer.parseInt(elem.findElement(qtyInput).getAttribute("value"));
            double subTotal = Double.parseDouble(elem.findElement(By.cssSelector(getCssPathFromTblHr("Subtotal"))).getText().replaceAll("[^\\d.]", ""));
            String currentItem = elem.findElement(By.cssSelector(getCssPathFromTblHr("Item"))).getText();
            Assert.assertEquals("Subtotal is incorrect for item: " + currentItem, itemPrice * qty, subTotal, 0.01);
        }
    }

    public void checkTotalOfAllItems() {
        double computedSum = 0;
        for (WebElement elem : cartItems) {
            double subTotal = Double.parseDouble(elem.findElement(By.cssSelector(getCssPathFromTblHr("Subtotal"))).getText().replaceAll("[^\\d.]", ""));
            computedSum = computedSum + subTotal;
        }
        double displayedTotal = Double.parseDouble(cartTotal.getText().replaceAll("[^\\d.]", ""));
        Assert.assertEquals("Incorrect Total amount", displayedTotal, computedSum, 0.01);
    }

}
