package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepOnePage extends BasePage {

    public static String URL = BASE_URL + "checkout-step-one.html";

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(css = "#first-name + .error_icon")
    private WebElement firstNameError;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(css = "#last-name + .error_icon")
    private WebElement lastNameError;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(css = "#postal-code + .error_icon")
    private WebElement postalCodeError;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMsgContainer;
    @FindBy(css = ".error-button")
    private WebElement clearErrorsButton;

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void waitPageToLoad() {
        mediumWait.until(ExpectedConditions.urlToBe(URL));
        mediumWait.until(ExpectedConditions.visibilityOf(firstNameField));
    }

    public void enterFirstName(String firstName) {
        enterText(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        enterText(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {
        enterText(postalCodeField, postalCode);
    }

    public void clickContinueButton() {
        clickElement(continueButton);
    }

    public Boolean isFirstNameErrorVisible() {
        try {
            return firstNameError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isLastNameErrorVisible() {
        try {
            return lastNameError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isPostCodeErrorVisible() {
        try {
            return postalCodeError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMsg() {
        mediumWait.until(ExpectedConditions.visibilityOf(errorMsgContainer));
        return errorMsgContainer.getText();
    }

    public void clearValidationError() {
        clickElement(clearErrorsButton);
    }

}
