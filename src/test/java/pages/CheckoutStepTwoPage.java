package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepTwoPage extends CartListContainer {

    public static String URL = BASE_URL + "checkout-step-two.html";
    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(css = ".summary_total_label")
    private WebElement totalPrice;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitPageToLoad() {
        mediumWait.until(ExpectedConditions.urlToBe(URL));
        mediumWait.until(ExpectedConditions.visibilityOf(finishButton));
    }

    public void clickFinishButton() {
        clickElement(finishButton);
    }

    public Double getTotalPrice() {
        return Double.parseDouble(totalPrice.getText().replaceAll("[^0-9.]", ""));
    }
}
