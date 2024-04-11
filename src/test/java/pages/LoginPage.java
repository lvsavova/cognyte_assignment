package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitPageToLoad() {
        mediumWait.until(ExpectedConditions.urlToBe(BASE_URL));
        mediumWait.until(ExpectedConditions.visibilityOf(usernameField));
    }
    public void enterUsername(String username) {
        enterText(usernameField, username);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public void loginWithUser(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
