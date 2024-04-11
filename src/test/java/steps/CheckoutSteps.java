package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.WebDriverHandler;

import java.util.List;
import java.util.Map;

public class CheckoutSteps {
    private WebDriver driver;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;
    private CheckoutCompletePage checkoutCompletePage;

    public CheckoutSteps() {
        driver = WebDriverHandler.setupDriver();
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
    }

    @When("I click the continue button")
    public void clickContinue() {
        checkoutStepOnePage.clickContinueButton();
    }

    @When("I enter the first name {string}")
    public void enterFirstName(String firstName) {
        checkoutStepOnePage.enterFirstName(firstName);
    }

    @When("I enter the last name {string}")
    public void enterLastName(String lastName) {
        checkoutStepOnePage.enterLastName(lastName);
    }

    @When("I enter the postcode {string}")
    public void enterPostCode(String postcode) {
        checkoutStepOnePage.enterPostalCode(postcode);
    }

    @When("I populate the checkout form with the following data:")
    public void populateCheckoutForm(DataTable dataTable) {
        List<Map<String, String>> userInfo = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> info : userInfo) {
            String fName = info.get("First Name");
            String lName = info.get("Last Name");
            String postCode = info.get("Postcode");
            enterFirstName(fName);
            enterLastName(lName);
            enterPostCode(postCode);
        }
    }

    @Then("the first name field should have validation error")
    public void validateFirstNameFieldError() {
        Assert.assertTrue(checkoutStepOnePage.isFirstNameErrorVisible(), "First name field should have validation error");
    }

    @Then("the first name field should not have validation error")
    public void validateFirstNameNoFieldError() {
        Assert.assertFalse(checkoutStepOnePage.isFirstNameErrorVisible(), "First name field should not have validation error");
    }

    @Then("the last name field should have validation error")
    public void validateLastNameFieldError() {
        Assert.assertTrue(checkoutStepOnePage.isLastNameErrorVisible(), "Last name field should have validation error");
    }

    @Then("the last name field should not have validation error")
    public void validateLastNameNoFieldError() {
        Assert.assertFalse(checkoutStepOnePage.isLastNameErrorVisible(), "Last name field should not have validation error");
    }

    @Then("the postcode field should have validation error")
    public void validatePostCodeFieldError() {
        Assert.assertTrue(checkoutStepOnePage.isPostCodeErrorVisible(), "Postcode field should have validation error");
    }

    @Then("the postcode field should not have validation error")
    public void validatePostCodeNoFieldError() {
        Assert.assertFalse(checkoutStepOnePage.isPostCodeErrorVisible(), "Postcode field should not have validation error");
    }

    @And("the form error message should be {string}")
    public void verifyFormErrorMsg(String expecedErrorMsg) {
        String actualErrorMsg = checkoutStepOnePage.getErrorMsg();
        Assert.assertTrue(actualErrorMsg.equals(expecedErrorMsg), "Error message is not as expected");
    }

    @And("I clear the validation error")
    public void clearValidationError() {
        checkoutStepOnePage.clearValidationError();
    }

    @And("the subtotal price should be {double}")
    public void verifyTotalPrice(double price) {
        double actualPrice = checkoutStepTwoPage.getTotalPrice();
        Assert.assertEquals(actualPrice, price, "Subtotal price is not as expected");
    }

    @And("I click the finish button")
    public void clickFinishButton() {
        checkoutStepTwoPage.clickFinishButton();
    }

    @And("I should see a header with text {string}")
    public void shouldSeeHeader(String msg) {
        Assert.assertEquals(checkoutCompletePage.getHeaderText(), msg, "Header text is not as expected");
    }
}
