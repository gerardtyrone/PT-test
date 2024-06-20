package pageObjects;

import common.pageObjects.BasePage;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopPage extends BasePage {

    @FindBy(css = "div.products")
    WebElement productsPageBody;

    @FindBys(value = @FindBy(css = "li[id^=product]"))
    private List<WebElement> productsList;

    private String itemNameLoc = "[class^='product-title']";
    private String priceLoc = "[class^='product-price']";
    private String buyBtnLoc = "[class*='btn-success']";

    private Map<String, Double> cartItems = new HashMap<>();

    public void isShopPageVisible() {
        driverWrapper.isElementVisible(productsPageBody);
    }

    public double selectProduct(String item){
        for (WebElement product : productsList) {
            String currentItem = product.findElement(By.cssSelector(itemNameLoc)).getText();
            if (item.equals(currentItem)){
                double itemPrice = Double.parseDouble(product.findElement(By.cssSelector(priceLoc)).getText().replaceAll("[^\\d.]", ""));
                driverWrapper.clickElement(product.findElement(By.cssSelector(buyBtnLoc)));
                return itemPrice;
            }
        }
        return 0;
    }

    public void addMultipleItemsToCart(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row: data) {
            int qty = Integer.parseInt(row.get("Qty"));
            String itemName = row.get("Item");
            for (int i = 0; i < qty ; i++) {
                double itemPrice = selectProduct(itemName);
                if(!cartItems.containsKey(itemName)) {
                    cartItems.put(itemName, itemPrice);
                }
            }
        }
        System.out.println(cartItems);
    }

    public Map<String, Double> getCartItems() {
        return cartItems;
    }

}
