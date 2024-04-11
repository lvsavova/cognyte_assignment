package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public class CartListContainer extends BasePage {

    public static String URL = BASE_URL + "cart.html";
    @FindBy(css = ".cart_list")
    private WebElement cartContainer;

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    private String itemNameLocator = ".inventory_item_name";
    private String itemQuantityLocator = ".cart_quantity";
    private String itemPriceLocator = ".inventory_item_price";

    public CartListContainer(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitPageToLoad() {
        mediumWait.until(ExpectedConditions.visibilityOf(cartContainer));
    }

    public int getItemQuantity(String name) {
        WebElement item = getItemByName(name);
        return getItemQuantity(item);
    }

    public double getItemPrice(String name) {
        WebElement item = getItemByName(name);
        return getItemPrice(item);
    }

    private double getItemPrice(WebElement item) {
        WebElement quantityElement = item.findElement(By.cssSelector(itemPriceLocator));
        mediumWait.until(ExpectedConditions.visibilityOf(quantityElement));
        return Double.parseDouble(quantityElement.getText().replaceAll("[^0-9.]", ""));
    }

    private int getItemQuantity(WebElement item) {
        WebElement quantityElement = item.findElement(By.cssSelector(itemQuantityLocator));
        mediumWait.until(ExpectedConditions.visibilityOf(quantityElement));
        return Integer.parseInt(quantityElement.getText());
    }
    private WebElement getItemByName(String name) {
        waitPageToLoad();
        for (WebElement item : cartItems) {
            String itemName = item.findElement(By.cssSelector(itemNameLocator)).getText();
            if (itemName.equals(name)) {
                return item;
            }
        }
        throw new NoSuchElementException("Item " + name + " is not found in the cart");
    }
}
