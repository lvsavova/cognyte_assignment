package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pages.CartListContainer;
import utils.WebDriverHandler;

import java.util.List;
import java.util.Map;

public class CartListSteps {
    private WebDriver driver;
    private CartListContainer cartListContainer;

    public CartListSteps() {
        driver = WebDriverHandler.setupDriver();
        cartListContainer = new CartListContainer(driver);
    }

    @Then("the cart should contain the following items:")
    public void cartContainsItems(DataTable dataTable) {
        List<Map<String, String>> items = dataTable.asMaps(String.class, String.class);
        SoftAssert softAssert = new SoftAssert();
        for (Map<String, String> item : items) {
            String itemName = item.get("Item");
            int quantity = Integer.parseInt(item.get("Quantity"));
            double price = Double.parseDouble(item.get("Price"));
            softAssert.assertEquals(cartListContainer.getItemQuantity(itemName), quantity, "Quantity of item " + itemName + " is not as expected");
            softAssert.assertEquals(cartListContainer.getItemPrice(itemName), price, "Price of item " + itemName + " is not as expected");
        }
        softAssert.assertAll();
    }
}
