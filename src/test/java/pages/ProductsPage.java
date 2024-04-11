package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import steps.NavigationSteps;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductsPage extends BasePage {
    public static String URL = BASE_URL + "inventory.html";

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inventory_container")
    private WebElement inventoryContainer;

    @FindBy(css = ".inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartContainer;

    private String itemNameLocator = ".inventory_item_name";
    private String itemAddButtonLocator = "button[id^='add-to-cart']";
    private String itemRemoveButtonLocator = "button[id^='remove']";

    @Override
    public void waitPageToLoad() {
        mediumWait.until(ExpectedConditions.urlToBe(URL));
        mediumWait.until(ExpectedConditions.visibilityOf(inventoryContainer));
    }

    public void addProduct(String name) {
        WebElement item = findItemByName(name);
        clickElement(item.findElement(By.cssSelector(itemAddButtonLocator)));
        try {
            mediumWait.until(ExpectedConditions.visibilityOf(item.findElement(By.cssSelector(itemRemoveButtonLocator))));
        } catch (TimeoutException e) {
            throw new RuntimeException("No remove button found for item " + name + " after adding it to the cart");
        }
    }

    public void removeProduct(String name) {
        WebElement item = findItemByName(name);
        clickElement(item.findElement(By.cssSelector(itemRemoveButtonLocator)));
        try {
            mediumWait.until(ExpectedConditions.visibilityOf(item.findElement(By.cssSelector(itemAddButtonLocator))));
        } catch (TimeoutException e) {
            throw new RuntimeException("No add button found for item " + name + " after removing it from the cart");
        }
    }

    public int getCartItemsCount() {
        mediumWait.until(ExpectedConditions.visibilityOf(cartContainer));
        try {
            smallWait.until(ExpectedConditions.visibilityOf(cartBadge));
            return Integer.parseInt(cartBadge.getText());
        } catch (TimeoutException e) {
            return 0;
        }
    }

    public void openCart() {
        clickElement(cartContainer);
        (new NavigationSteps()).shouldLandPage("Cart");
    }

    private WebElement findItemByName(String name) {
        mediumWait.until(ExpectedConditions.visibilityOf(inventoryContainer));
        for (WebElement item : inventoryItems) {
            String itemName = item.findElement(By.cssSelector(itemNameLocator)).getText();
            if (itemName.equals(name)) {
                return item;
            }
        }
        throw new NoSuchElementException("Item " + name + " is not found in the inventory");
    }
}
