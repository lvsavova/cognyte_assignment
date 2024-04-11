package steps;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import utils.WebDriverHandler;

public class CartSteps {
    private WebDriver driver;
    private CartPage cartPage;

    public CartSteps() {
        driver = WebDriverHandler.setupDriver();
        cartPage = new CartPage(driver);
    }

    @When("I click the checkout button")
    public void clickCheckout() {
        cartPage.clickCheckoutButton();
    }

}
