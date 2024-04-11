package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BurgerMenu extends BasePage {

    public static String URL = BASE_URL + "cart.html";

    @FindBy(id = "react-burger-menu-btn")
    private WebElement openMenuButton;
    @FindBy(css = ".bm-menu-wrap")
    private WebElement menuWrap;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItemsButton;

    @FindBy(id = "about_sidebar_link")
    private WebElement aboutButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    public BurgerMenu(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void waitPageToLoad() {
        mediumWait.until(ExpectedConditions.visibilityOf(menuWrap));
    }

    public void openMenu() {
        clickElement(openMenuButton);
        waitPageToLoad();
    }
    public void clickAllItemsButton() {
        clickElement(allItemsButton);
    }

    public void clickAboutButton() {
        clickElement(aboutButton);
    }

    public void clickLogoutButton() {
        clickElement(logoutButton);
    }
}
