package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ProductsPage;
import utils.WebDriverHandler;

import java.util.List;

public class ProductsSteps {

    private WebDriver driver;
    private ProductsPage productsPage;

    public ProductsSteps() {
        driver = WebDriverHandler.setupDriver();
        productsPage = new ProductsPage(driver);
    }

    @When("I add the product {string} to the cart")
    public void addProduct(String productName) {
        productsPage.addProduct(productName);
    }

    @When("I add the following products to the cart:")
    public void addProducts(DataTable dataTable) {
        List<String> productNames = dataTable.asList(String.class);
        for (String productName : productNames) {
            addProduct(productName);
        }
    }

    @When("I remove the product {string} from the cart")
    public void removeProduct(String productName) {
        productsPage.removeProduct(productName);
    }

    @When("I remove the following products from the cart:")
    public void removeProducts(DataTable dataTable) {
        List<String> productNames = dataTable.asList(String.class);
        for (String productName : productNames) {
            removeProduct(productName);
        }
    }

    @Then("the number of items in the cart should be {int}")
    public void verifyItemsCount(int expectedCount) {
        int actualCount = productsPage.getCartItemsCount();
        Assert.assertEquals(actualCount, expectedCount, "Incorrect number of items in the cart");
    }

    @When("I navigate to the cart")
    public void openCart() {
        productsPage.openCart();
    }
}
