package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import pageObjects.ContactPage;
import pojo.ContactInfo;

import java.util.List;
import java.util.Map;

public class contactPageStepDef implements En {

    private ContactInfo contactInfo;

    public contactPageStepDef(ScenarioHooks hooks, ContactPage contactPage) {

        Then("^I should see the Contact page$", () -> {
            contactPage.setDriver(hooks.getDriverWrapper());
            contactPage.isContactPageVisible();
        });

        When("^I click on Submit", contactPage::clickSubmit);

        Then("^I should see the following error messages$", contactPage::validateErrorMsgs);

        When("^I populate the mandatory fields$", (DataTable dataTable) -> {
            List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
            Map<String, String> dataInput = data.get(0);
            contactPage.enterForename(dataInput.get("Forename"));
            contactPage.enterEmail(dataInput.get("Email"));
            contactInfo = new ContactInfo();
            contactInfo.setForename(dataInput.get("Forename"));
            contactPage.enterMessage(dataInput.get("Message"));
        });

        Then("^I should not see any errors on the page$", contactPage::validateErrorElements);

        Then("^I should see successful submission message$", () -> {
            contactPage.validateSuccessMsg(contactInfo.getForename());
        });
    }
}
