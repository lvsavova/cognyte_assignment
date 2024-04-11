package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.WebDriverHandler;


public class NavigationSteps {

    private WebDriver driver;

    public NavigationSteps() {
        driver = WebDriverHandler.setupDriver();
    }

    @Given("I open the saucedemo site")
    public void openSite() {
        driver.get(BasePage.BASE_URL);
    }

    @Then("I am at the {string} page")
    public void shouldLandPage(String page) {
        switch (page) {
            case "Login":
                new LoginPage(driver).waitPageToLoad();
                break;
            case "Products":
                new ProductsPage(driver).waitPageToLoad();
                break;
            case "Cart":
                new CartPage(driver).waitPageToLoad();
                break;
            case "Checkout Overview":
                new CheckoutStepTwoPage(driver).waitPageToLoad();
                break;
            case "Checkout Complete":
                new CheckoutCompletePage(driver).waitPageToLoad();
                break;
            default:
                throw new RuntimeException("No implementation for page " + page);
        }
    }
}
