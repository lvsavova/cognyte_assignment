package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import pages.BurgerMenu;
import pages.LoginPage;
import utils.WebDriverHandler;

public class LoginLogoutSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    private BurgerMenu burgerMenu;

    public LoginLogoutSteps() {
        driver = WebDriverHandler.setupDriver();
        loginPage = new LoginPage(driver);
        burgerMenu = new BurgerMenu(driver);
    }

    @Given("I login with user {string} and password {string}")
    public void loginWithUser(String username, String password) {
        loginPage.loginWithUser(username, password);
    }

    @And("I logout")
    public void logout() {
        burgerMenu.openMenu();
        burgerMenu.clickLogoutButton();
        loginPage.waitPageToLoad();
    }
}
