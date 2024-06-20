package stepDefinitions;

import common.pageObjects.CommonPage;
import io.cucumber.java8.En;
import pageObjects.HomePage;

public class landingPageStepDef implements En {

    public landingPageStepDef(ScenarioHooks hooks, HomePage homePage, CommonPage commonPage) {

        String url = "http://jupiter.cloud.planittesting.com";

        Given("^I launch the Jupiter Toys website$", () -> {
            hooks.driverWrapper.get(url);
        });

        Then("^I should see Jupiter Toys homepage$", () -> {
            homePage.setDriver(hooks.getDriverWrapper());
            homePage.isHomepageVisible();
        });

        And("^I click on (?:(Home|Shop|Contact|Login|Cart)) from navbar$", (String nav) -> {
            commonPage.setDriver(hooks.getDriverWrapper());
            commonPage.clickOnNavItemByText(nav.toLowerCase());
        });
    }
}
