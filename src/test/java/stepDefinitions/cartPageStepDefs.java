package stepDefinitions;

import io.cucumber.java8.En;
import pageObjects.CartPage;
import pageObjects.ShopPage;

import java.util.Map;

public class cartPageStepDefs implements En {

    private Map<String, Double> cartItems;

    public cartPageStepDefs(ScenarioHooks hooks, CartPage cartPage, ShopPage shopPage) {

        Then("^I should see the Cart page$", () -> {
            cartPage.setDriver(hooks.getDriverWrapper());
            cartPage.isCartPageVisible();
        });

        Then("^I should see the correct prices for each item$", () -> {
            cartItems = shopPage.getCartItems();
            for (Map.Entry<String, Double> entry : cartItems.entrySet()) {
                cartPage.checkItemAndPrice(entry.getKey(), entry.getValue().toString());
            }
        });

        Then("^I should see the correct subtotal for each item$", cartPage::checkSubtotalOfEachItem);

        Then("^I should see the correct total for all items in cart$", cartPage::checkTotalOfAllItems);
    }

}
