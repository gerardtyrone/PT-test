package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import pageObjects.ShopPage;
import pojo.ContactInfo;

import java.util.List;
import java.util.Map;

public class shopPageStefDefs implements En {


    public shopPageStefDefs(ScenarioHooks hooks, ShopPage shopPage) {

        Then("^I should see the Shop page$", () -> {
            shopPage.setDriver(hooks.getDriverWrapper());
            shopPage.isShopPageVisible();
        });

        When("^I add the following items to cart$", shopPage::addMultipleItemsToCart);

    }
}
